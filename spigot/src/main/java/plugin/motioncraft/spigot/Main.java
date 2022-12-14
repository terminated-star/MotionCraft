package plugin.motioncraft.spigot;

import org.bukkit.plugin.java.JavaPlugin;
import plugin.motioncraft.MotionCraft;
import plugin.motioncraft.spigot.event.ListenerHealthFood;
import plugin.motioncraft.spigot.event.ListenerJoinQuit;
import plugin.motioncraft.spigot.event.ListenerJumpPaper;
import plugin.motioncraft.spigot.event.ListenerTick;

public class Main extends JavaPlugin {
	private static Main instance;
	private final MotionCraft motionCraft = new MotionCraft();
	private final ListenerTick listenerTick = new ListenerTick();

	@Override
	public void onEnable() {
		instance = this;

		MotionCraft.getApi().setPlatformAPI(new SpigotPlatformAPI());
		motionCraft.onEnable();

		getServer().getPluginManager().registerEvents(new ListenerJoinQuit(), this);
		getServer().getPluginManager().registerEvents(new ListenerHealthFood(), this);
		//getServer().getPluginManager().registerEvents(new ListenerJumpPaper(), this);
		listenerTick.start();
	}

	@Override
	public void onDisable() {
		motionCraft.onDisable();
		listenerTick.stop();
	}

	public static Main getInstance() {
		return instance;
	}
}
