

import java.io.*; 

/**
 * Text messages send to server. There're 4 kinds of text messages (request):
 * status, logout, chat message and user list.
 * 
 * @author Longxin Li
 *
 */
public  class  TextMessage  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3677961382520112152L;

	
	/**
	 * Make kinds of request in Integers.
	 */
	public static final int STATUS = 0, LOGOUT = 1, MESSAGE = 2, USERLIST = 3, HELP = 9;

	
	/**
	 * Kind of request, currently 4 kinds of requests are available: check
	 * status, logout, chat and check the users in chat room.
	 */
	private int req;

	
	/**
	 * Content of message.
	 */
	private String content;

	

	public TextMessage(int req, String content) {
		super();
		this.req = req;
		this.content = content;

	}

	

	public int getReq() {
		return req;
	}

	

	public String getContent() {
		return content;
	}


}
