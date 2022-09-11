package plugin.motioncraft.user;

import plugin.motioncraft.MotionCraft;
import plugin.motioncraft.common.CommonVector;

import java.util.UUID;

public class CommonUser {
	private final UUID id;

	// TODO: Should this also contain data such as the speed, etc.? it seems weird since it's also a proxy class

	public CommonUser(UUID id) {
		this.id = id;
	}

	public UUID getId() {
		return id;
	}

	public boolean isSprinting() {
		return MotionCraft.getApi().getPlatformAPI().isSprinting(this);
	}

	public boolean isSneaking() {
		return MotionCraft.getApi().getPlatformAPI().isSneaking(this);
	}

	public CommonVector getLookDirection() {
		return MotionCraft.getApi().getPlatformAPI().getLookDirection(this);
	}

	public CommonVector getVelocity() {
		return MotionCraft.getApi().getPlatformAPI().getVelocity(this);
	}

	public void setVelocity(CommonVector velocity) {
		MotionCraft.getApi().getPlatformAPI().setVelocity(this, velocity);
	}
}
