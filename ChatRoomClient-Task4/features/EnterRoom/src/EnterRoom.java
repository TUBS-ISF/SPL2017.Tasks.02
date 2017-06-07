import interfaces.IEnable;

public class EnterRoom implements IEnable {
	

	@Override
	public boolean enable() {
		System.out.println("- EnterRoom module activated.");
		return true;
	}
}
