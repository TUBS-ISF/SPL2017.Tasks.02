import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Basic functions, including enter room, chat (send/receive text message), log
 * in, change room etc.
 * 
 * @author Longxin Li
 *
 */
public class Basic {

	//#if Receive
	private ObjectInputStream sInput;
	//#endif
	private ObjectOutputStream sOutput;
	private Socket socket;
	private String host = "localhost";
	int port = 8080;

	/**
	 * First step to start client. Connect to server and listen the port.
	 * 
	 * @return True if client started, False if not.
	 */
	public boolean start() {		
		try {
			socket = new Socket(host, port);
		} catch (Exception ec) {
			showMsg("Error connectiong to server:" + ec);
			return false;
		}
		String msg = "Connection accepted " + socket.getInetAddress() + ":" + socket.getPort();
		showMsg(msg);
		try {
			//#if Receive
			sInput = new ObjectInputStream(socket.getInputStream());
			//#endif
			sOutput = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException eIO) {
			showMsg("Exception creating new Input/output Streams: " + eIO);
			return false;
		}
		//#if Receive
		new ListenFromServer().start();
		//#endif
		try {
			//Let's make default anonymous
			String userName = "Anonymous";
			// The login function is not available because of no database.
			// Just make this to show the possibility
			//#if LogIn
//@			userName = "VIP user";
			//#endif
			sOutput.writeObject(userName);
			showMsg(userName + " logged in.");
		} catch (IOException eIO) {
			showMsg("Exception doing login : " + eIO);
			disconnect();
			return false;
		}
		return true;
	}

	/**
	 * Run the client. Send message to server.
	 */
	public void run() {
		if (!start())
			return;
		// #if Send
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
		// when finish then disconnect
		disconnect();
		scan.close();
		// #endif
	}

	// #if Send
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
	// #endif

	/**
	 * Display message in console. Reserve future function for GUI.
	 * 
	 * @param msg
	 *            message.
	 */
	private void showMsg(String msg) {
		System.out.println(msg);
	}

	/**
	 * Disconnect and close all the streams and socket.
	 */
	public void disconnect() {
		//#if Receive
		if (sInput != null) {
			try {
				sInput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//#endif
		if (sOutput != null) {
			try {
				sOutput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Basic() {
		// #if ChangeRoom
//@		Scanner scan = new Scanner(System.in);
//@		try {
//@			System.out.print("Please input server adress:");
//@			host = scan.nextLine();
//@			System.out.print("Please input port number:");
//@			port = Integer.parseInt(scan.nextLine());
//@		} catch (Exception e) {
//@			System.out.println("Wrong Input.");
//@		}
//@		// scan.close();
		// #endif
		run();
	}

	//#if Receive
	// Listener, get this code from tutorial
	// http://www.dreamincode.net/forums/topic/259777-a-simple-chat-program-with-clientserver-gui-optional/
	class ListenFromServer extends Thread {
		public void run() {
			while (true) {
				try {
					String msg = (String) sInput.readObject();
					System.out.println(msg);
					System.out.print("> ");
				} catch (Exception e) {
					showMsg("Server has close the connection: " + e);
					break;
				}
			}
		}
	}
	//#endif
}
