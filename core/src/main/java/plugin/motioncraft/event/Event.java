package plugin.motioncraft.event;

public class Event {
	protected boolean cancellable = false;
	private boolean cancelled = false;

	public boolean isCancellable() {
		return cancellable;
	}

	public boolean isCancelled() {
		return cancelled;
	}
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
}
