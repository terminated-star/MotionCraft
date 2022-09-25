package plugin.motioncraft.game;

import plugin.motioncraft.event.Event;
import plugin.motioncraft.event.EventListener;
import plugin.motioncraft.event.impl.EventFoodLevelChange;

public class ListenerMisc implements EventListener {
	@Override
	public void onEvent(Event eventBase) {
		if (eventBase instanceof EventFoodLevelChange) {
			EventFoodLevelChange event = (EventFoodLevelChange) eventBase;
			if (event.getUser().isPlaying()) {
				// TODO: Let them gain food but not lose it
				event.setCancelled(true);
			}
		}
	}
}
