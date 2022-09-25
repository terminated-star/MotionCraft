package plugin.motioncraft.api;

import plugin.motioncraft.common.Callback;
import plugin.motioncraft.common.CommonLocation;
import plugin.motioncraft.common.CommonVector;
import plugin.motioncraft.user.CommonUser;

public interface PlatformAPI {
	public <T> T runSync(Callback<T> callback);

	public String getName(CommonUser user);
	public boolean isSprinting(CommonUser user);
	public boolean isSneaking(CommonUser user);
	public boolean isRiding(CommonUser user);
	public boolean isOnGround(CommonUser user);
	public boolean isInLiquid(CommonUser user);
	public CommonLocation getLocation(CommonUser user);
	public void setLocation(CommonUser user, CommonLocation location);
	public CommonVector getLookDirection(CommonUser user);
	public CommonVector getVelocity(CommonUser user);
	public void setVelocity(CommonUser user, CommonVector velocity);
	public void sendMessage(CommonUser user, String text);
	public void sendActionBar(CommonUser user, String text);

	public boolean canPlay(CommonUser user);
}
