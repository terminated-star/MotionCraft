package plugin.motioncraft.game;

import plugin.motioncraft.MotionCraft;
import plugin.motioncraft.event.Event;
import plugin.motioncraft.event.EventListener;

public class GameCore implements EventListener {
	public void onStart() {
		MotionCraft.getApi().getEventManager().register(new ListenerMain());
	}

	public void onStop() {

	}

	@Override
	public void onEvent(Event eventBase) {

	}
}
