package Client;

import Client.Basic.BasicClient;
import Client.GUI.ClientGUI;
import properties.PropertyManager;

/**
 * This is the main class for client based on Feature-IDE using property
 * manager.
 * 
 * @author Longxin Li
 *
 */
public class Client {
	
	// Set default value for simply testing
	private static String host = "localhost";
	private static int port = 8080;
	
	public static void main(String[] args) throws Exception {
		String lang = "EN";
		// String oS;

		// I don't really know the difference when running java program on
		// different OS, just reserve these options and simple output
		if (PropertyManager.getProperty("Linux")) {
			System.out.print("This client is for Linux.");
		}
		if (PropertyManager.getProperty("Win")) {
			System.out.print("This client is for Windows.");
		}
		if (PropertyManager.getProperty("MacOS")) {
			System.out.print("This client is for MacOS.");
		}

		// Languages, not good features but still needed for UI
		if (PropertyManager.getProperty("EN")) {
			// default
			System.out.println(" English version.");
		}
		if (PropertyManager.getProperty("DE")) {
			lang = "DE";
			System.out.println(" German version.");
		}
		if (PropertyManager.getProperty("ZH")) {
			lang = "ZH";
			System.out.println(" Chinese version.");
		}

		// Real features, most of these features should be implemented in any
		// case
		if (PropertyManager.getProperty("GUI")) {
			// Currently not a good solution
			ClientGUI gui = new ClientGUI(lang);
			gui.run();
		} else {
			// init a BaseClient with default host and port
			// in the future might be able to connect to different servers
			// TODO: Is that really needed for a chatroom?
			BasicClient baseClient = new BasicClient(host, port, lang);
			baseClient.run();
		}
	}
}
