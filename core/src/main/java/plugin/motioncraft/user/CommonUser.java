package plugin.motioncraft.user;

import plugin.motioncraft.MotionCraft;
import plugin.motioncraft.common.CommonVector;

import java.util.UUID;

public class CommonUser {
	private final UUID id;
	private double horizontalSpeed = 0;
	private double verticalSpeed = 0; // TODO: this isn't speed, since -5 would be going down, that would technically be 5 speed
	private int ticksOnGround = 0;
	private int ticksInAir = 0;

	// TODO: Should this also contain data such as the speed, etc.? it seems weird since it's also a proxy class

	public CommonUser(UUID id) {
		this.id = id;
	}

	public UUID getId() {
		return id;
	}

	public double getSpeed() {
		return horizontalSpeed;
	}
	public void setSpeed(double horizontalSpeed) {
		if (horizontalSpeed < 0) return;
		this.horizontalSpeed = horizontalSpeed;
	}
	public double getVerticalSpeed() {
		return verticalSpeed;
	}
	public void setVerticalSpeed(double verticalSpeed) {
		this.verticalSpeed = verticalSpeed;
	}

	// We shouldn't have things like startSpeed in here as they might be map specific, we should call some MapManager for that. Although we can have a getter here that proxies that.
	public double getStartSpeed() {
		return 400.0;
	}
	public boolean isGroundSlidingEnabled() {
		return true;
	}
	public boolean isGroundAccelerationEnabled() {
		return false;
	}
	public int getSafeGroundTicks() {
		return 1;
	}

	public boolean isSprintingNormally() {
		return isSprinting() && !isSneaking();
	}
	public boolean isSprinting() {
		return MotionCraft.getApi().getPlatformAPI().isSprinting(this);
	}
	public boolean isSneaking() {
		return MotionCraft.getApi().getPlatformAPI().isSneaking(this);
	}
	public boolean isOnGround() {
		return MotionCraft.getApi().getPlatformAPI().isOnGround(this);
	}
	public boolean isInLiquid() {
		return MotionCraft.getApi().getPlatformAPI().isInLiquid(this);
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
	public void sendActionBar(String text) {
		MotionCraft.getApi().getPlatformAPI().sendActionBar(this, text);
	}

	public int getTicksOnGround() {
		return ticksOnGround;
	}
	public void setTicksOnGround(int ticksOnGround) {
		this.ticksOnGround = ticksOnGround;
	}
	public int getTicksInAir() {
		return ticksInAir;
	}
	public void setTicksInAir(int ticksInAir) {
		this.ticksInAir = ticksInAir;
	}

	public boolean isPlaying() {
		return true;
	}
}
