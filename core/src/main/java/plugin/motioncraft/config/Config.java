package plugin.motioncraft.config;

public class Config {
	private boolean useAsync = false; // Using async will cause a tick of delay, at least on Spigot servers
	private boolean useJumpEvent = true; // Whether to use the jump event when on paper to detect jumps
	// TODO: Have multiple jump detection algorithms

	public boolean isUseAsyncEnabled() {
		return useAsync;
	}
	public boolean isUseJumpEventEnabled() {
		return useJumpEvent;
	}
}
