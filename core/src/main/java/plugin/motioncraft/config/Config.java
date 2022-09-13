package plugin.motioncraft.config;

public class Config {
	private boolean useAsync = false; // Using async will cause a tick of delay, at least on Spigot servers

	public boolean isUseAsyncEnabled() {
		return useAsync;
	}
}
