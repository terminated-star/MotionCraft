package plugin.motioncraft.spigot.event;

import org.bukkit.Bukkit;
import plugin.motioncraft.MotionCraft;
import plugin.motioncraft.event.Event;
import plugin.motioncraft.event.impl.EventTick;
import plugin.motioncraft.spigot.Main;

public class ListenerTick  {
	public void start() {
		Bukkit.getServer().getScheduler().runTaskTimerAsynchronously(Main.getInstance(), () -> {
			Event eventAsync = new EventTick(false);
			MotionCraft.getApi().getEventManager().post(eventAsync);

			// We call this in here so we can be sure that the sync task is always run after the async one
			Bukkit.getServer().getScheduler().runTask(Main.getInstance(), () -> {
				Event eventSync = new EventTick(true);
				MotionCraft.getApi().getEventManager().post(eventSync);
			});

		}, 0L, 1L);

		/*Bukkit.getServer().getScheduler().runTaskTimer(Main.getInstance(), () -> {
			Event event = new EventTick(true);
			MotionCraft.getApi().getEventManager().post(event);
		}, 0L, 1L);*/
	}

	public void stop() {

	}
}
