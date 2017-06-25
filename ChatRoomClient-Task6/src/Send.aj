import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public aspect Send {

	declare precedence: Text , Receive, Send, EnterRoom;

	pointcut run(): execution(void Basic.start());

	pointcut start():execution(void Actions.run());

	after():run(){
		System.out.println(" - Send module loaded.");
	}

	after():start(){
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.print("> ");

			String msg = scan.nextLine();
			if (msg.equalsIgnoreCase("/LOGOUT")) {
				send(new TextMessage(TextMessage.LOGOUT, ""));
				break;
			} else if (msg.equalsIgnoreCase("/USERLIST")) {
				send(new TextMessage(TextMessage.USERLIST, ""));
			} else if (msg.equalsIgnoreCase("/STATUS")) {
				send(new TextMessage(TextMessage.STATUS, ""));
			} else if (msg.equalsIgnoreCase("/HELP")) {
				send(new TextMessage(TextMessage.HELP, ""));
			} else {
				send(new TextMessage(TextMessage.MESSAGE, msg));
			}
		}
		scan.close();
		System.exit(0);
	}

	private ObjectOutputStream sOutput;

	/**
	 * Send text message to server.
	 * 
	 * @param msg
	 *            message
	 */
	public void send(TextMessage msg) {

		try {
			sOutput.writeObject(msg);
		} catch (IOException e) {
			showMsg("Exception writing to server: " + e);
		}
	}

	private void showMsg(String msg) {
		System.out.println(msg);
	}
}