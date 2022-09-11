package plugin.motioncraft.api;

import plugin.motioncraft.event.EventManager;
import plugin.motioncraft.user.UserManager;

public class MotionCraftAPI {
	private final UserManager userManager = new UserManager();
	private final EventManager eventManager = new EventManager();
	private PlatformAPI platformAPI;

	public UserManager getUserManager() {
		return userManager;
	}
	public EventManager getEventManager() {
		return eventManager;
	}
	public PlatformAPI getPlatformAPI() {
		return platformAPI;
	}
	public void setPlatformAPI(PlatformAPI platformAPI) {
		this.platformAPI = platformAPI;
	}

	public double getTickRate() {
		return 20.0;
	}
	public double getGameSpeed() {
		return getTickRate()/20.0;
	}
}
