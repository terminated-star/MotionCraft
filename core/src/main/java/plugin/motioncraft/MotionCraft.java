package plugin.motioncraft;

import plugin.motioncraft.api.MotionCraftAPI;
import plugin.motioncraft.event.listener.jump.ListenerJumpVelocityA;
import plugin.motioncraft.game.GameCore;
import plugin.motioncraft.game.ListenerMisc;
import plugin.motioncraft.user.UserHandler;

public class MotionCraft {
	private static final MotionCraftAPI api = new MotionCraftAPI();
	private final GameCore gameCore = new GameCore();

	// TODO: Maybe use an event manager for the on enable and on disable?
	public void onEnable() {
		getApi().getEventManager().register(new UserHandler());

		gameCore.onStart();
		getApi().getEventManager().register(gameCore);
		getApi().getEventManager().register(new ListenerMisc());
		getApi().getEventManager().register(new ListenerJumpVelocityA());
	}

	public void onDisable() {
		getApi().getEventManager().unregisterAll();
	}

	public static MotionCraftAPI getApi() {
		return api;
	}
}
