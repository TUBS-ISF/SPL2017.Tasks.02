import interfaces.ISetOS;

public class Mac implements ISetOS {

	@Override
	public void showOS() {
		System.out.println("This is MacOS version.");
	}

}
