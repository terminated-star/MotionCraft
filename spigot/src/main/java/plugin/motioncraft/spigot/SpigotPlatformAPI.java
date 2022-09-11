package plugin.motioncraft.spigot;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import plugin.motioncraft.api.PlatformAPI;
import plugin.motioncraft.common.Callback;
import plugin.motioncraft.common.CommonVector;
import plugin.motioncraft.user.CommonUser;

public class SpigotPlatformAPI implements PlatformAPI {
	public Player getPlayer(CommonUser user) {
		// TODO: Use a callback for this to make sure it is synced
		return Bukkit.getPlayer(user.getId());
	}

	@Override
	public <T> T runSync(Callback<T> callback) {
		if (Bukkit.isPrimaryThread())
			return callback.call();
		else {
			Bukkit.getScheduler().runTask(Main.getInstance(), new Runnable() {
				@Override
				public void run() {
					callback.call();
				}
			});
			return null; // TODO: Return the correct value
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
	public CommonVector getLookDirection(CommonUser user) {
		Player platformUser = getPlayer(user); // TODO: Move this inside the runSync
		Vector platformVelocity = platformUser.getLocation().getDirection(); // TODO: Move this inside the runSync
		return new CommonVector(platformVelocity.getX(), platformVelocity.getY(), platformVelocity.getZ());
	}

	@Override
	public CommonVector getVelocity(CommonUser user) {
		Player platformUser = getPlayer(user); // TODO: Move this inside the runSync
		Vector platformVelocity = platformUser.getVelocity(); // TODO: Move this inside the runSync
		return new CommonVector(platformVelocity.getX(), platformVelocity.getY(), platformVelocity.getZ());
	}

	@Override
	public void setVelocity(CommonUser user, CommonVector velocity) {
		runSync((Callback<Void>) () -> {
			Player platformUser = getPlayer(user);
			Vector platformVelocity = new Vector(velocity.getX(), velocity.getY(), velocity.getZ());
			platformUser.setVelocity(platformVelocity);
			return null;
		});
	}
}
