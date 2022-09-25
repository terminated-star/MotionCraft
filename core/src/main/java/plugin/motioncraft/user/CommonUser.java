package plugin.motioncraft.user;

import plugin.motioncraft.MotionCraft;
import plugin.motioncraft.common.CommonLocation;
import plugin.motioncraft.common.CommonVector;

import java.util.UUID;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CommonUser {
	private final UUID id;
	private double horizontalSpeed = 0;
	private double veloY = 0; // TODO: this isn't speed, since -5 would be going down, that would technically be 5 speed
	private int ticksOnGround = 0;
	private int ticksInAir = 0;
	private CommonLocation lastGroundLocation;

	// Locks
	private final ReentrantReadWriteLock horizontalSpeedLock = new ReentrantReadWriteLock();
	private final ReentrantReadWriteLock verticalSpeedLock = new ReentrantReadWriteLock();
	private final ReentrantReadWriteLock ticksOnGroundLock = new ReentrantReadWriteLock();
	private final ReentrantReadWriteLock ticksInAirLock = new ReentrantReadWriteLock();
	private final ReentrantReadWriteLock lastGroundLocationLock = new ReentrantReadWriteLock();

	// TODO: Should this also contain data such as the speed, etc.? it seems weird since it's also a proxy class

	public CommonUser(UUID id) {
		this.id = id;
	}

	public UUID getId() {
		return id;
	}

	public double getSpeed() {
		horizontalSpeedLock.readLock().lock();
		try {
			return horizontalSpeed;
		} finally {
			horizontalSpeedLock.readLock().unlock();
		}
	}
	public void setSpeed(double horizontalSpeed) {
		if (horizontalSpeed < 0) return;

		horizontalSpeedLock.writeLock().lock();
		try {
			this.horizontalSpeed = horizontalSpeed;
		} finally {
			horizontalSpeedLock.writeLock().unlock();
		}
	}
	public double getVeloY() {
		verticalSpeedLock.readLock().lock();
		try {
			return veloY;
		} finally {
			verticalSpeedLock.readLock().unlock();
		}
	}
	public void setVeloY(double veloY) {
		verticalSpeedLock.writeLock().lock();
		try {
			this.veloY = veloY;
		} finally {
			verticalSpeedLock.writeLock().unlock();
		}
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

	public String getName() {
		return MotionCraft.getApi().getPlatformAPI().getName(this);
	}
	public boolean isSprintingNormally() {
		return isSprinting() && !isSneaking() && !isRiding();
	}
	public boolean isSprinting() {
		return MotionCraft.getApi().getPlatformAPI().isSprinting(this);
	}
	public boolean isSneaking() {
		return MotionCraft.getApi().getPlatformAPI().isSneaking(this);
	}
	public boolean isRiding() {
		return MotionCraft.getApi().getPlatformAPI().isRiding(this);
	}
	public boolean isOnGround() {
		return MotionCraft.getApi().getPlatformAPI().isOnGround(this);
	}
	public boolean isInLiquid() {
		return MotionCraft.getApi().getPlatformAPI().isInLiquid(this);
	}
	public CommonLocation getLocation() {
		return MotionCraft.getApi().getPlatformAPI().getLocation(this);
	}
	public void setLocation(CommonLocation location) {
		MotionCraft.getApi().getPlatformAPI().setLocation(this, location);
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
	public void sendMessage(String text) {
		MotionCraft.getApi().getPlatformAPI().sendMessage(this, text);
	}
	public void sendActionBar(String text) {
		MotionCraft.getApi().getPlatformAPI().sendActionBar(this, text);
	}

	public int getTicksOnGround() {
		ticksOnGroundLock.readLock().lock();
		try {
			return ticksOnGround;
		} finally {
			ticksOnGroundLock.readLock().unlock();
		}
	}
	public void setTicksOnGround(int ticksOnGround) {
		ticksOnGroundLock.writeLock().lock();
		try {
			this.ticksOnGround = ticksOnGround;
		} finally {
			ticksOnGroundLock.writeLock().unlock();
		}
	}
	public int getTicksInAir() {
		ticksInAirLock.readLock().lock();
		try {
			return ticksInAir;
		} finally {
			ticksInAirLock.readLock().unlock();
		}
	}
	public void setTicksInAir(int ticksInAir) {
		ticksInAirLock.writeLock().lock();
		try {
			this.ticksInAir = ticksInAir;
		} finally {
			ticksInAirLock.writeLock().unlock();
		}
	}

	public CommonLocation getLastGroundLocation() {
		lastGroundLocationLock.readLock().lock();
		try {
			return lastGroundLocation;
		} finally {
			lastGroundLocationLock.readLock().unlock();
		}
	}
	public void setLastGroundLocation(CommonLocation lastGroundLocation) {
		lastGroundLocationLock.writeLock().lock();
		try {
			this.lastGroundLocation = lastGroundLocation;
		} finally {
			lastGroundLocationLock.writeLock().unlock();
		}
	}

	public boolean isPlaying() {
		// The platform shouldn't handle everything, such as if they run a command to exit the game, that should be platform dependent
		return MotionCraft.getApi().getPlatformAPI().canPlay(this);
	}
}
