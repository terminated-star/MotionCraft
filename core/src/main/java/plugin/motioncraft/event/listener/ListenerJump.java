package plugin.motioncraft.event.listener;

import plugin.motioncraft.MotionCraft;
import plugin.motioncraft.event.Event;
import plugin.motioncraft.event.EventListener;
import plugin.motioncraft.event.impl.EventJump;
import plugin.motioncraft.event.impl.EventTick;
import plugin.motioncraft.user.CommonUser;

public class ListenerJump implements EventListener {
	// TODO: Detect if in a liquid
	// TODO: Make this better

	@Override
	public void onEvent(Event eventBase) {
		if (eventBase instanceof EventTick) {
			EventTick event = (EventTick) eventBase;

			if (event.isSynced()) {
				for (CommonUser user : MotionCraft.getApi().getUserManager().getUsers()) {
					//user.sendMessage("Velocity Y: " + user.getVelocity().getY());
					if (user.getVelocity().getY() == -0.03) {
						EventJump eventJump = new EventJump(user);
						MotionCraft.getApi().getEventManager().post(eventJump);
					}
				}
			}
		}
	}
}
