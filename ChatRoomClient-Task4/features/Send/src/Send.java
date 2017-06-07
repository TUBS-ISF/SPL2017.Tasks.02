import interfaces.IEnable;

public class Send implements IEnable {

	@Override
	public boolean enable() {
		System.out.println("- Send function activated.");
		return false;
	}

}
