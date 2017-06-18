
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * GUI for client, based on JFrame.
 * 
 * @author Longxin Li
 *
 */
public class GUI extends JFrame implements ActionListener {

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

	public GUI() {
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

	public void start(String[] texts) {
		this.texts = texts;
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
		System.out.println("- GUI activated.");
		this.setVisible(true);
	}
}
