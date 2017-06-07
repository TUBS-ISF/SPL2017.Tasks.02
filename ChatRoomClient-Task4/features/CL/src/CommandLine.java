import interfaces.IStartUI;

public class CommandLine implements IStartUI {

	@Override
	public void start(String[] texts) {
		System.out.println("- Command line activated.");
	}

}
