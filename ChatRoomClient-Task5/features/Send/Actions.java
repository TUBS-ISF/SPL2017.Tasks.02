import java.io.IOException;
import java.util.Scanner;

public class Actions {
	/**
	 * Run the client. Send message to server.
	 */
	public void run() {
		original();
		Scanner scan = new Scanner(System.in);
		while (flag) {
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
		disconnect();
		scan.close();
	}

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
}
