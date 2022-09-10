package plugin.motioncraft.user;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
	private final List<CommonUser> users = new ArrayList<>();

	public void addUser(CommonUser user) {
		getUsers().add(user);
	}

	public void removeUser(CommonUser user) {
		getUsers().remove(user);
	}

	public List<CommonUser> getUsers() {
		return users;
	}
}
