package plugin.motioncraft.game;

import plugin.motioncraft.MotionCraft;
import plugin.motioncraft.common.CommonVector;
import plugin.motioncraft.event.Event;
import plugin.motioncraft.event.EventListener;
import plugin.motioncraft.event.impl.EventTick;
import plugin.motioncraft.user.CommonUser;

public class ListenerMain implements EventListener {
	@Override
	public void onEvent(Event eventBase) {
		if (eventBase instanceof EventTick event) {
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
		}
	}

	public void doTick(CommonUser user) {
		double gameSpeed = MotionCraft.getApi().getGameSpeed();

		CommonVector velocity = user.getLookDirection();

		double yaw = Math.atan2(velocity.getX(), velocity.getZ());
		velocity.setX(Math.sin(yaw));
		velocity.setZ(Math.cos(yaw));

		user.setVelocity(velocity);
	}
}
