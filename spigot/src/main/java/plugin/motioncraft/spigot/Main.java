package plugin.motioncraft.spigot;

import org.bukkit.plugin.java.JavaPlugin;
import plugin.motioncraft.MotionCraft;
import plugin.motioncraft.spigot.event.ListenerJoinQuit;
import plugin.motioncraft.spigot.event.ListenerTick;

public class Main extends JavaPlugin {
	private static Main instance;
	private ListenerTick listenerTick;

	@Override
	public void onEnable() {
		MotionCraft.getApi().setPlatformAPI(new SpigotPlatformAPI());

		getServer().getPluginManager().registerEvents(new ListenerJoinQuit(), this);
		listenerTick.start();
	}

	@Override
	public void onDisable() {
		listenerTick.stop();
	}

	public static Main getInstance() {
		return instance;
	}
}
