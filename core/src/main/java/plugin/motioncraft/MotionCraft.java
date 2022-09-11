package plugin.motioncraft;

import plugin.motioncraft.api.MotionCraftAPI;
import plugin.motioncraft.game.GameCore;
import plugin.motioncraft.user.UserHandler;

public class MotionCraft {
	private static final MotionCraftAPI api = new MotionCraftAPI();

	// TODO: Maybe use an event manager for the on enable and on disable?
	public void onEnable() {
		getApi().getEventManager().register(new UserHandler());
		getApi().getEventManager().register(new GameCore());
	}

	public void onDisable() {
		getApi().getEventManager().unregisterAll();
	}

	public static MotionCraftAPI getApi() {
		return api;
	}
}
