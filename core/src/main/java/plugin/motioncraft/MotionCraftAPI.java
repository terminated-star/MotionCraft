package plugin.motioncraft;

import plugin.motioncraft.user.UserManager;

public class MotionCraftAPI {
	private final UserManager userManager = new UserManager();

	public UserManager getUserManager() {
		return userManager;
	}
}
