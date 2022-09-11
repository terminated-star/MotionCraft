package plugin.motioncraft.user;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
	private final List<CommonUser> users = new ArrayList<>();

	public void add(CommonUser user) {
		getUsers().add(user);
	}

	public void remove(CommonUser user) {
		getUsers().remove(user);
	}

	public List<CommonUser> getUsers() {
		return users;
	}
}
