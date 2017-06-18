import java.io.IOException; 
import java.io.ObjectInputStream; 
import java.io.ObjectOutputStream; 
import java.net.Socket; 
import java.util.Scanner; 

public   class  Actions {
	
	private ObjectInputStream sInput  ;

	
	private ObjectOutputStream sOutput;

	
	private Socket socket;

	
	private String host = "localhost";

	
	int port = 8080;

	
	boolean flag = true;

	
	/**
	 * First step to start client. Connect to server and listen the port.
	 * 
	 * @return True if client started, False if not.
	 */
	 private boolean  start__wrappee__EnterRoom  () {		
		try {
			socket = new Socket(host, port);
		} catch (Exception ec) {
			showMsg("Error connectiong to server:" + ec);
			return false;
		}
		String msg = "Connection accepted " + socket.getInetAddress() + ":" + socket.getPort();
		showMsg(msg);
		initStreams();
		try {
			//Let's make default anonymous
			String userName = "Anonymous";
			// The login function is not available because of no database.
			// Just make this to show the possibility
			sOutput.writeObject(userName);
			showMsg(userName + " logged in.");
		} catch (IOException eIO) {
			showMsg("Exception doing login : " + eIO);
			disconnect();
			return false;
		}
		return true;
	}

	
	
	
	
	
	public boolean start() {
		System.out.println("- Able to receive message.");
		new ListenFromServer().start();
		return start__wrappee__EnterRoom();		
	}

	
	
	 private void  initStreams__wrappee__EnterRoom  (){
		try {
			sOutput = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException eIO) {
			showMsg("Exception creating new Output Stream: " + eIO);
		}
	}

	
	
	private void initStreams(){
		initStreams__wrappee__EnterRoom();
		try {
			sInput = new ObjectInputStream(socket.getInputStream());
		} catch (IOException eIO) {
			showMsg("Exception creating new Input Stream: " + eIO);
		}
	}

	
	
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
	 private void  disconnect__wrappee__EnterRoom  () {
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

	
	
	
	public void disconnect() {
		if (sInput != null) {
			try {
				sInput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		disconnect__wrappee__EnterRoom();
	}

	
	
	/**
	 * Run the client. Send message to server.
	 */
	 private void  run__wrappee__EnterRoom  () {
		if (!start()){
			flag = false;
			return;}
	}

	
	/**
	 * Run the client. Send message to server.
	 */
	public void run() {
		run__wrappee__EnterRoom();
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

	
	
	 
	
	class  ListenFromServer  extends Thread {
		
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
