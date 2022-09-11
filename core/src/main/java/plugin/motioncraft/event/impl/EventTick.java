package plugin.motioncraft.event.impl;

import plugin.motioncraft.event.Event;

public class EventTick extends Event {
	private final boolean synced; // TODO: Invert the name of this function, something like isSafe

	public EventTick(boolean synced) {
		this.synced = synced;
	}

	public boolean isSynced() {
		return synced;
	}
}
