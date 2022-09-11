package plugin.motioncraft.event.impl;

import plugin.motioncraft.event.Event;
import plugin.motioncraft.user.CommonUser;

public class EventJoin extends Event {
	private final CommonUser user;

	public EventJoin(CommonUser user) {
		this.user = user;
	}

	public CommonUser getUser() {
		return user;
	}
}
