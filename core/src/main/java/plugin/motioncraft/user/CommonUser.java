package plugin.motioncraft.user;

import java.util.UUID;

public class CommonUser {
	private final UUID id;

	public CommonUser(UUID id) {
		this.id = id;
	}

	public UUID getId() {
		return id;
	}
}
