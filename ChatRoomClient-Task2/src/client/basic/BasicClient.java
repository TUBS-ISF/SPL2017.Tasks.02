package client.basic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import client.message.TextMessage;
import properties.PropertyManager;

/*
 * This is not a part of SPL, but the base of all future components.
 */

/**
 * Core functions of ChatRoomClient. Most functions in this class should be
 * implemented in all the variants.
 * 
 * @author Longxin Li
 *
 */

// TODO: Login function should be added, might use a new constructor to get
// started. Another idea is to add Login function into Client.java.
public class BasicClient {

	private ObjectInputStream sInput;
	private ObjectOutputStream sOutput;
	private Socket socket;
	private String[] texts;
	private String host;
	int port;

	/**
	 * First step to start client. Connect to server and listen the port.
	 * 
	 * @return True if client started, False if not.
	 */
	public boolean start() {
		// This should not be anonymous, but login function not work
		// just set userName anonymous for simple further work
		String userName = "Anonymous";
		try {
			socket = new Socket(host, port);
		} catch (Exception ec) {
			showMsg("Error connectiong to server:" + ec);
			return false;
		}
		String msg = "Connection accepted " + socket.getInetAddress() + ":" + socket.getPort();
		showMsg(msg);
		try {
			sInput = new ObjectInputStream(socket.getInputStream());
			sOutput = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException eIO) {
			showMsg("Exception creating new Input/output Streams: " + eIO);
			return false;
		}
		new ListenFromServer().start();
		try {
			sOutput.writeObject(userName);
		} catch (IOException eIO) {
			showMsg("Exception doing login : " + eIO);
			disconnect();
			return false;
		}
		System.out.println("Client started.");
		return true;
	}

	/**
	 * Run the client. Send message to server.
	 */
	public void run() {
		if (!start())
			return;
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
		if (sInput != null) {
			try {
				sInput.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (sOutput != null) {
			try {
				sOutput.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Load from file "lang.xml" in folder "res". This is the same function as
	 * in {@link Client.GUI.ClientGUI}.
	 * 
	 * @param lang
	 *            chosen language
	 */
	private void fillTexts(String lang) {
		String file = "/res/lang.xml";
		// default size is 5
		texts = new String[5];
		try {
			String path = System.getProperty("user.dir");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(path + file);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("language");
			for (int i = 0; i < nList.getLength(); i++) {
				Node langNode = nList.item(i);
				if (langNode.getAttributes().item(0).getNodeValue().equals(lang)) {
					texts[0] = doc.getElementsByTagName("welcome").item(i).getTextContent();
					texts[1] = doc.getElementsByTagName("login").item(i).getTextContent();
					texts[2] = doc.getElementsByTagName("exit").item(i).getTextContent();
					// etc.
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Loading language file error! Please check if lang.xml is correctly.");
		}
	}

	public BasicClient(String host, int port, String lang) {
		this.host = host;
		this.port = port;
		fillTexts(lang);
		if (PropertyManager.getProperty("Send")) {
			System.out.println("- Send function is available.");
		}
		if (PropertyManager.getProperty("Receive")) {
			System.out.println("- Receive function is available.");
		}
		if (PropertyManager.getProperty("EnterRoom")) {
			System.out.println("- EnterRoom function is available.");
		}
		if (PropertyManager.getProperty("LogIn")) {
			System.out.println("- Log-in function is activated.");
		}
	}

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
}
