package plugin.motioncraft.event.impl;

import plugin.motioncraft.event.Event;
import plugin.motioncraft.user.CommonUser;

public class EventFoodLevelChange extends Event {
	private final CommonUser user;

	public EventFoodLevelChange(CommonUser user) {
		this.cancellable = true;

		this.user = user;
	}

	public CommonUser getUser() {
		return user;
	}
}
