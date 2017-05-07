package client.basic;

/**
 * Log-in function. Currently abandoned because there's no database for users.
 * 
 * @author Longxin Li
 *
 */
public class LogIn {

	private String userName;
	private String password;
	private int Role;

	public LogIn() {
		super();
		System.out.println("Here should be a login function, currently only fake.");
	}

	public LogIn(String name, String pwd) {
		super();
		this.userName = name;
		this.password = pwd;
		// Verification in database should be implemented here.
		System.out.println("Here should be a login function, currently only fake.");
	}

	public UserInfo login() {
		UserInfo user = new UserInfo(userName, Role);
		// currently fake function
		user.setRole(3);
		return user;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return Role;
	}

	public void setRole(int role) {
		Role = role;
	}

}
