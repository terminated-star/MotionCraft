package plugin.motioncraft.user;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserManager {
	private final List<CommonUser> users = new ArrayList<>();

	public void add(CommonUser user) {
		getUsers().add(user);
	}

	public void remove(CommonUser user) {
		getUsers().remove(user);
	}

	public CommonUser getFromId(UUID id) {
		for (CommonUser user : getUsers()) {
			if (id.equals(user.getId())) return user;
		}
		return null;
	}

	public List<CommonUser> getUsers() {
		return users;
	}
}
