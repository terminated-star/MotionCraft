package plugin.motioncraft.spigot.event;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import plugin.motioncraft.MotionCraft;
import plugin.motioncraft.event.Event;
import plugin.motioncraft.event.impl.EventJump;
import plugin.motioncraft.user.CommonUser;

public class ListenerJumpPaper implements Listener {
	@EventHandler
	public void onJump(PlayerJumpEvent platformEvent) {
		CommonUser user = MotionCraft.getApi().getUserManager().getFromId(platformEvent.getPlayer().getUniqueId());
		Event event = new EventJump(user);
		MotionCraft.getApi().getEventManager().post(event);
	}
}
