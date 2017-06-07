import interfaces.IEnable;

public class Text implements IEnable {

	@Override
	public boolean enable() {
		System.out.println("- Now able to send text.");
		return false;
	}

}
