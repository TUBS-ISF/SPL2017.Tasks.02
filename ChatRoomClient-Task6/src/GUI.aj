import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Aspect can not implement serializable, we have to use an additional class.
 * 
 * @author Li Longxin
 *
 */
public aspect GUI {

	// declare precedence: GUI, Basic;

	pointcut run(): execution(void Basic.start());

	after():run(){
		new GUI_C().start();
		System.out.println(" - GUI activated.");
	}

}

/**
 * GUI for client, based on JFrame.
 * 
 * @author Longxin Li
 *
 */
class GUI_C extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Login Buttons.
	 */
	private JButton login, exit;

	// More possible buttons: export log, ban, invite, change password etc.

	/**
	 * String array to save text for labels and buttons.
	 */
	private String[] texts = { "0", "1", "2" };

	// TODO: It's much better to combine GUI with BasicClient (or Console),
	// some
	// functions are duplicated. But currently no solution for that.

	// private BasicClient basic;

	public String[] getTexts() {
		return texts;
	}

	public void setTexts(String[] texts) {
		this.texts = texts;
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

	public void start(String texts[]) {
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
		// System.out.println("- GUI activated.");
		this.setVisible(true);
	}

	public void start() {
		// start(texts);
	}

}