package Client.GUI;

import javax.swing.*;
import javax.xml.parsers.*;

import org.w3c.dom.*;

import Client.Client;

import java.awt.*;
import java.awt.event.*;

/**
 * GUI for client, based on JFrame.
 * 
 * @author Longxin Li
 *
 */
public class ClientGUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8415933731423178089L;

	/**
	 * Login Buttons.
	 */
	private JButton login, exit;

	// More possible buttons: export log, ban, invite, change password etc.

	/**
	 * String array to save text for labels and buttons.
	 */
	private String[] texts;

	// TODO: It's much better to combine GUI with BasicClient (or Console), some
	// functions are duplicated. But currently no solution for that.

	// private BasicClient basic;

	public ClientGUI(String lang) {

		// TODO: GUI need BasicClient (or Console), some functions are
		// duplicated. But currently no solution for that.
		super(lang);
		// Load text from lang.xml
		fillTexts(lang);

		// Add a button for login at the bottom
		login = new JButton(texts[1]);
		login.addActionListener(this);

		// Add a button for exit
		exit = new JButton(texts[2]);
		exit.addActionListener(this);

		JPanel southPanel = new JPanel();
		southPanel.add(login);
		southPanel.add(exit);
		add(southPanel, BorderLayout.SOUTH);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(360, 480);
		setVisible(true);
	}

	/**
	 * Load text from file "lang.xml" in folder "res". This function is just a
	 * tmp version.
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

	/**
	 * Getting started.
	 */
	public void run() {
		System.out.println("- GUI activated.");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		// Not really needed, just to show it works for different languages
		if (o == exit) {
			System.exit(0);
		} else if (o == login) {
			// Dummy function
			JOptionPane.showMessageDialog(this, texts[0], null, JOptionPane.PLAIN_MESSAGE);
		}
	}
}
