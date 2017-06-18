import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Actions {
	private ObjectInputStream sInput;
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
	public boolean start() {		
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
	
	private void initStreams(){
		try {
			sOutput = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException eIO) {
			showMsg("Exception creating new Output Stream: " + eIO);
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
	public void disconnect() {
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
	
	/**
	 * Run the client. Send message to server.
	 */
	public void run() {
		if (!start()){
			flag = false;
			return;}
	}


}
