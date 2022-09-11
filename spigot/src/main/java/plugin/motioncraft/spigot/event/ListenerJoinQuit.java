package plugin.motioncraft.spigot.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import plugin.motioncraft.MotionCraft;
import plugin.motioncraft.event.Event;
import plugin.motioncraft.event.impl.EventJoin;
import plugin.motioncraft.event.impl.EventQuit;
import plugin.motioncraft.user.CommonUser;

public class ListenerJoinQuit implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent platformEvent) {
		CommonUser user = new CommonUser(platformEvent.getPlayer().getUniqueId());
		Event event = new EventJoin(user);
		MotionCraft.getApi().getEventManager().post(event);
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent platformEvent) {
		CommonUser user = new CommonUser(platformEvent.getPlayer().getUniqueId());
		Event event = new EventQuit(user);
		MotionCraft.getApi().getEventManager().post(event);
	}
}
