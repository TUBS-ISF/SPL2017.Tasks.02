import java.io.Serializable;

/**
 * Aspect can not implement serializable. We have to use nested class.
 * 
 * @author Li Longxin
 *
 */
public aspect Text {

	declare precedence: Text, Receive, Send, EnterRoom;

	pointcut run(): execution(void Basic.start());

	after():run(){
		System.out.println(/* this.getClass().getName()+ */" - Text module loaded.");
	}
}

/**
 * Text messages send to server. There're 4 kinds of text messages (request):
 * status, logout, chat message and user list.
 * 
 * @author Longxin Li
 *
 */
class TextMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
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