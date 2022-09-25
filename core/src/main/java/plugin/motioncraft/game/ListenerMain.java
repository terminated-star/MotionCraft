package plugin.motioncraft.game;

import plugin.motioncraft.MotionCraft;
import plugin.motioncraft.common.CommonVector;
import plugin.motioncraft.event.Event;
import plugin.motioncraft.event.EventListener;
import plugin.motioncraft.event.impl.EventJump;
import plugin.motioncraft.event.impl.EventTick;
import plugin.motioncraft.user.CommonUser;

import java.text.NumberFormat;

public class ListenerMain implements EventListener {

	@Override
	public void onEvent(Event eventBase) {
		if (eventBase instanceof EventTick) {
			EventTick event = (EventTick) eventBase;
			if (!event.isSynced()) {
				// TODO: What happens if the player gets disconnected in the middle of our calculations while async? Is this even possible? If so, we just need to not do anything when called in the user class, or the platform api maybe
				// Calculations
				for (CommonUser user : MotionCraft.getApi().getUserManager().getUsers()) {
					if (!user.isPlaying()) continue;
					doTickAsync(user);
				}
			} else {
				// Setting the velocity
				for (CommonUser user : MotionCraft.getApi().getUserManager().getUsers()) {
					if (!user.isPlaying()) continue;
					doTickSync(user);
				}
			}
		} else if (eventBase instanceof EventJump) {
			EventJump event = (EventJump) eventBase;
			CommonUser user = event.getUser();

			//user.ticksSinceJump = -1;

			// TODO: Let the tick events actually set it
			user.setVeloY(0.2);

			// TODO: should we tp the user here or in the tick events??
			//if (jumpFixMode.equals(JumpFixMode.CANCEL)) {
			if (user.getLastGroundLocation() != null)
				user.setLocation(user.getLastGroundLocation());
			//}
		}
	}

	public void doTickAsync(CommonUser user) {
		processGeneralData(user);
		user.setSpeed(tickHorizontalSpeed(user));
		user.setVeloY(tickVelocityY(user));
	}

	public void doTickSync(CommonUser user) {
		CommonVector velocity = user.getLookDirection();
		double yaw = Math.atan2(velocity.getX(), velocity.getZ());
		velocity.setX(Math.sin(yaw));
		velocity.setZ(Math.cos(yaw));
		velocity.multiply(user.getSpeed() / 1500);
		velocity.setY(user.getVeloY());

		user.setVelocity(velocity);

		//String speedDisplay = String.format("%,.0f", Math.ceil(user.getSpeed()));
		String speedDisplay = NumberFormat.getNumberInstance().format((int)Math.ceil(user.getSpeed()));
		user.sendActionBar(speedDisplay);
	}

	public double tickHorizontalSpeed(CommonUser user) {
		// TODO should be an interface, with multiple speed modes like strafe and forward
		// TODO: Then we should maybe have a manager for it, like AlgorithmManager for horizontal and vertical speed
		double horizontalSpeed = user.getSpeed();

		// Acceleration
		if (user.isSprintingNormally()) {
			if (horizontalSpeed < user.getStartSpeed()) {
				double speedAdd = user.getStartSpeed()/10;
				speedAdd = Math.min(speedAdd, user.getStartSpeed()); // TODO remove this line
				horizontalSpeed += speedAdd;
			}

			if (user.isGroundAccelerationEnabled() || user.getTicksOnGround() <= user.getSafeGroundTicks()) {
				horizontalSpeed += 5;
				/*if (accelMode.equals(AccelerationMode.FORWARD))
					horizontalSpeed += 5;
				else if (accelMode.equals(AccelerationMode.ROTATION)) {
					double rotationDistance = getRotationDistance(user);
					horizontalSpeed += rotationDistance/10;
					// TODO make a rotation distance max, -1 to disable
				}*/
			}
		}

		// Deceleration
		if (user.getTicksOnGround() > user.getSafeGroundTicks()) {
			//	if (playerData.isSneaking()) {
			//		horizontalSpeed -= 200;
			//	}
			if (!user.isSprintingNormally() || !user.isGroundSlidingEnabled()) {
				horizontalSpeed -= 100;
			}
		} else {
			if (!user.isSprintingNormally()) {
				horizontalSpeed *= 0.9;
			}
		}

		return horizontalSpeed;
	}

	public double tickVelocityY(CommonUser user) {
		double veloY = user.getVeloY();

		double downwardMotion = 0.005 * user.getTicksInAir();
		if (downwardMotion > 0.02) downwardMotion = 0.02;
		veloY -= downwardMotion;

		if (user.isOnGround())
			veloY = 0;

		if (user.isInLiquid())
			veloY = 0.3;

		return veloY;
	}

	// maybe rename to doDataTick ?
	public void processGeneralData(CommonUser user) {
		if (user.isOnGround()) {
			user.setTicksOnGround(user.getTicksOnGround() + 1);
			user.setTicksInAir(0);


			user.setLastGroundLocation(user.getLocation());
		} else {
			user.setTicksOnGround(0);
			user.setTicksInAir(user.getTicksInAir() + 1);
		}
	}

	public double getRotationDistance(CommonUser user) {
		return 5;
	}
}
