package plugin.motioncraft.user;

import plugin.motioncraft.MotionCraft;
import plugin.motioncraft.event.Event;
import plugin.motioncraft.event.EventListener;
import plugin.motioncraft.event.impl.EventJoin;
import plugin.motioncraft.event.impl.EventJump;
import plugin.motioncraft.event.impl.EventQuit;

public class UserHandler implements EventListener {
	@Override
	public void onEvent(Event eventBase) {
		if (eventBase instanceof EventJoin) {
			EventJoin event = (EventJoin) eventBase;
			MotionCraft.getApi().getUserManager().add(event.getUser());
		}
		else if (eventBase instanceof EventQuit) {
			EventQuit event = (EventQuit) eventBase;
			MotionCraft.getApi().getUserManager().remove(event.getUser());
		}
	}
}
