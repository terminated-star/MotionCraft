package plugin.motioncraft.event.impl;

import plugin.motioncraft.user.CommonUser;

public class EventJump {
	private final CommonUser user;

	public EventJump(CommonUser user) {
		this.user = user;
	}

	public CommonUser getUser() {
		return user;
	}
}
