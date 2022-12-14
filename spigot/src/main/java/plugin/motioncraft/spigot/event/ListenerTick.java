package plugin.motioncraft.spigot.event;

import org.bukkit.Bukkit;
import plugin.motioncraft.MotionCraft;
import plugin.motioncraft.event.Event;
import plugin.motioncraft.event.impl.EventTick;
import plugin.motioncraft.spigot.Main;

public class ListenerTick  {
	public void start() {
		if (MotionCraft.getApi().getConfig().isUseAsyncEnabled()) {
			Bukkit.getServer().getScheduler().runTaskTimerAsynchronously(Main.getInstance(), () -> {
				Event eventAsync = new EventTick(false);
				MotionCraft.getApi().getEventManager().post(eventAsync);

				// TODO: The runTask waits until the next tick, by that time another async event might have run
				// We call this in here so we can be sure that the sync task is always run after the async one
				Bukkit.getServer().getScheduler().runTask(Main.getInstance(), () -> {
					Event eventSync = new EventTick(true);
					MotionCraft.getApi().getEventManager().post(eventSync);
				});

			}, 0L, 1L);
		} else {
			Bukkit.getServer().getScheduler().runTaskTimer(Main.getInstance(), () -> {
				Event eventAsync = new EventTick(false); // We still send the async event so that any code that relies on the event will still work, but it isn't actually async
				MotionCraft.getApi().getEventManager().post(eventAsync);

				Event eventSync = new EventTick(true);
				MotionCraft.getApi().getEventManager().post(eventSync);
			}, 0L, 1L);
		}
	}

	public void stop() {

	}
}
