import interfaces.IEnable;

public class Receive implements IEnable {

	@Override
	public boolean enable() {
		System.out.println("- Recieve module activated.");
		return false;
	}

}
