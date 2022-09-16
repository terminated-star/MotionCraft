package plugin.motioncraft.spigot;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import plugin.motioncraft.api.PlatformAPI;
import plugin.motioncraft.common.Callback;
import plugin.motioncraft.common.CommonVector;
import plugin.motioncraft.user.CommonUser;

import java.util.concurrent.*;

public class SpigotPlatformAPI implements PlatformAPI {
	public Player getPlayer(CommonUser user) {
		return runSync(() -> Bukkit.getPlayer(user.getId()));
	}

	@Override
	public <T> T runSync(Callback<T> callback) {
		if (Bukkit.isPrimaryThread()) {
			return callback.call();
		} else {
			Future<T> future = Bukkit.getScheduler().callSyncMethod(Main.getInstance(), callback::call);
			try {
				return future.get();
			} catch (java.lang.InterruptedException | ExecutionException e) { return null; }
		}
	}

	@Override
	public boolean isSprinting(CommonUser user) {
		return runSync(() -> {
			Player platformUser = getPlayer(user);
			return platformUser.isSprinting();
		});
	}

	@Override
	public boolean isSneaking(CommonUser user) {
		return runSync(() -> {
			Player platformUser = getPlayer(user);
			return platformUser.isSneaking();
		});
	}

	@Override
	public boolean isOnGround(CommonUser user) {
		return runSync(() -> {
			Player platformUser = getPlayer(user);
			return platformUser.isOnGround();
		});
	}

	@Override
	public boolean isInLiquid(CommonUser user) {
		return runSync(() -> {
			Player platformUser = getPlayer(user);
			return platformUser.getLocation().getBlock().isLiquid() || platformUser.getLocation().add(0, 1, 0).getBlock().isLiquid();
		});
	}

	@Override
	public CommonVector getLookDirection(CommonUser user) {
		return runSync(() -> {
			Player platformUser = getPlayer(user);
			Vector platformVelocity = platformUser.getLocation().getDirection();
			return new CommonVector(platformVelocity.getX(), platformVelocity.getY(), platformVelocity.getZ());
		});
	}

	@Override
	public CommonVector getVelocity(CommonUser user) {
		return runSync(() -> {
			Player platformUser = getPlayer(user);
			Vector platformVelocity = platformUser.getVelocity();
			return new CommonVector(platformVelocity.getX(), platformVelocity.getY(), platformVelocity.getZ()); // TODO: See if it would matter if we removed this from the runSync, the getters here aren't part of bukkit's api
		});
	}

	@Override
	public void setVelocity(CommonUser user, CommonVector velocity) {
		runSync(() -> {
			Player platformUser = getPlayer(user);
			Vector platformVelocity = new Vector(velocity.getX(), velocity.getY(), velocity.getZ());
			platformUser.setVelocity(platformVelocity);
			return null;
		});
	}

	@Override
	public void sendMessage(CommonUser user, String text) {
		runSync(() -> {
			Player platformUser = getPlayer(user);
			platformUser.sendMessage(text);
			return null;
		});
	}

	@Override
	public void sendActionBar(CommonUser user, String text) {
		if (Main.getInstance().getServer().getVersion().contains("(MC: 1.8")) return; // TODO: Support action bars on older servers, they don't support the way we are doing it below
		runSync(() -> {
			Player platformUser = getPlayer(user);
			platformUser.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(text));
			return null;
		});
	}
}
