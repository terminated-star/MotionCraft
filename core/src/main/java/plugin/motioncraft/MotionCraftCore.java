package plugin.motioncraft;

public class MotionCraftCore {
	private static final MotionCraftAPI api = new MotionCraftAPI();

	public void onEnable() {

	}

	public void onDisable() {

	}

	public static MotionCraftAPI getApi() {
		return api;
	}
}
