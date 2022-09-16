package plugin.motioncraft.game;

import plugin.motioncraft.MotionCraft;
import plugin.motioncraft.common.CommonVector;
import plugin.motioncraft.event.Event;
import plugin.motioncraft.event.EventListener;
import plugin.motioncraft.event.impl.EventJump;
import plugin.motioncraft.event.impl.EventTick;
import plugin.motioncraft.user.CommonUser;

public class ListenerMain implements EventListener {
	@Override
	public void onEvent(Event eventBase) {
		if (eventBase instanceof EventTick) {
			EventTick event = (EventTick) eventBase;
			/*if (!event.isSynced()) {
				// Calculations
			} else {
				// Setting the velocity
			}*/

			if (event.isSynced()) {
				for (CommonUser user : MotionCraft.getApi().getUserManager().getUsers()) {
					doTick(user);
				}
			}
		} else if (eventBase instanceof EventJump) {
			EventJump event = (EventJump) eventBase;
			CommonUser user = event.getUser();

			//user.ticksSinceJump = -1;

			user.setVerticalSpeed(0.2);

			// Testing start
			//playerData.ticksInAir = 0;
			// Testing end

			//if (jumpFixMode.equals(JumpFixMode.CANCEL)) {
			//	event.setCancelled(true);
			//}
		}
	}

	public void doTick(CommonUser user) {
		processGeneralData(user);
		updateHorizontalSpeed(user);
		updateVerticalSpeed(user);

		CommonVector velocity = user.getLookDirection();
		double yaw = Math.atan2(velocity.getX(), velocity.getZ());
		velocity.setX(Math.sin(yaw));
		velocity.setZ(Math.cos(yaw));
		velocity.multiply(user.getSpeed() / 1500);
		velocity.setY(user.getVerticalSpeed());

		user.setVelocity(velocity);

		String speedDisplay = String.format("%,.0f", Math.ceil(user.getSpeed()));
		user.sendActionBar(speedDisplay);
	}

	public void updateHorizontalSpeed(CommonUser user) {
		// TODO should be an interface, with multiple speed modes like strafe and forward
		// TODO: Then we should maybe have a manager for it, like AlgorithmManager for horizontal and vertical speed

		// Acceleration
		if (user.isSprinting()) {
			if (user.getSpeed() < user.getStartSpeed()) {
				double speedAdd = user.getStartSpeed()/10;
				speedAdd = Math.min(speedAdd, user.getStartSpeed());
				user.setSpeed(user.getSpeed() + speedAdd);
			}

			if (user.isGroundAccelerationEnabled() || user.getTicksOnGround() <= user.getSafeGroundTicks()) {
				user.setSpeed(user.getSpeed() + 5);
				/*if (accelMode.equals(AccelerationMode.FORWARD))
					user.setSpeed(user.getSpeed() + 5);
				else if (accelMode.equals(AccelerationMode.ROTATION)) {
					double rotationDistance = getRotationDistance(user);
					user.setSpeed(user.getSpeed() + (rotationDistance/10));
					// TODO make a rotation distance max, -1 to disable
				}*/
			}
		}

		// Deceleration
		if (user.getTicksOnGround() > user.getSafeGroundTicks()) {
			//	if (playerData.isSneaking()) {
			//		playerData.setSpeed(playerData.getSpeed() - 200);
			//	}
			if (!user.isSprinting() || !user.isGroundSlidingEnabled()) {
				user.setSpeed(user.getSpeed() - 100);
			}
		} else {
			if (!user.isSprinting()) {
				user.setSpeed(user.getSpeed() * 0.9);
			}
		}
	}

	public void updateVerticalSpeed(CommonUser user) {
		// Vertical

		// TODO set their motion y to 0 if on ground
		double downwardMotion = 0.005 * user.getTicksInAir();
		if (downwardMotion > 0.02) downwardMotion = 0.02;
		user.setVerticalSpeed(user.getVerticalSpeed() - downwardMotion);

		if (user.isOnGround())
			user.setVerticalSpeed(0);

		if (user.isInLiquid())
			user.setVerticalSpeed(0.3);
	}

	public void processGeneralData(CommonUser user) {
		if (user.isOnGround()) {
			user.setTicksOnGround(user.getTicksOnGround() + 1);
			user.setTicksInAir(0);
		} else {
			user.setTicksOnGround(0);
			user.setTicksInAir(user.getTicksInAir() + 1);
		}
	}

	public double getRotationDistance(CommonUser user) {
		return 5;
	}
}
