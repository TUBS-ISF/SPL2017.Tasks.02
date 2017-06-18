
public   class  Main {
	

	public static void main(String[] args) {
		new Main().start();
	}

	
	
	 private void  start__wrappee__Basic  (){
		System.out.println("Loading modules...");
	}

	
	 private void  start__wrappee__EnterRoom  (){
		start__wrappee__Basic();
		Actions as = new Actions();
		as.run();
		System.out.println("- Able to enter a chat room.");
	}

	
	 private void  start__wrappee__GUI  () {
		start__wrappee__EnterRoom();
		GUI gui = new GUI();
		// TODO: Add English backup string array can be better
		gui.start(new Lang().getLang());
	}

	
	private void start() {
		start__wrappee__GUI();
		System.out.println("- Windows style.");
	}


}
