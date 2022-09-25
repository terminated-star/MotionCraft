package plugin.motioncraft.event;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
	private final List<EventListener> listeners = new ArrayList<>();

	public void register(EventListener listener) {
		getListeners().add(listener);
	}
	public void unregister(EventListener listener) {
		getListeners().remove(listener);
	}

	public boolean post(Event event) {
		for (EventListener eventListener : getListeners()) {
			eventListener.onEvent(event);
		}
		return event.isCancelled();
	}

	private List<EventListener> getListeners() {
		return listeners;
	}

	public void unregisterAll() {
		for (EventListener listener : getListeners()) {
			unregister(listener);
		}
	}
}
