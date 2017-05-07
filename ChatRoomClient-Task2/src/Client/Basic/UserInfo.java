package Client.Basic;

/**
 * User data, will be used for advanced management function such as moderation.
 * Currently not implemented. Reserved.
 * 
 * @author Longxin Li
 *
 */
public class UserInfo {
	/**
	 * Roles for the chat room.
	 */
	static final int ADMIN = 0, MODERATOR = 1, USER = 2, VISITOR = 3;

	private String userName;
	private int Role;

	public UserInfo(String userName, int role) {
		super();
		this.userName = userName;
		this.Role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getRole() {
		return Role;
	}

	public void setRole(int role) {
		Role = role;
	}

}
