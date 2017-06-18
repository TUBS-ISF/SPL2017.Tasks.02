
public class Main {
	private void start() {
		original();
		GUI gui = new GUI();
		// TODO: Add English backup string array can be better
		gui.start(new Lang().getLang());
	}
}
