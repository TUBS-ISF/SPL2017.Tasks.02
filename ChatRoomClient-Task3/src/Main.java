/**
 * Main class to start client.
 * @author Longxin Li
 *
 */
public class Main {

	public static void main(String[] args) {
		System.out.println("Loading following modules:");
		// The first part is always OS, only reserved.
		
		// #if Win
		System.out.println("- Windows");
		// #endif
		// #if Linux
//@		System.out.println("- Linux");
		// #endif
		// #if MacOS
//@		System.out.println("- MacOS");
		// #endif
		 
		//select if run in console or GUI mode.
		 
		//#if CL
//@		System.out.println("- Command Line");
//@		Console cl = new Console();
		//#endif
		// #if GUI
		System.out.println("- GUI");
		GUI gui = new GUI();
		gui.run();
		// #endif
	}

}
