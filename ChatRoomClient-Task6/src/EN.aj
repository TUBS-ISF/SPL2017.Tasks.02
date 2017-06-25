
public aspect EN {
	
	pointcut gui_start(): execution(void GUI_C.start());
	pointcut gui():call(GUI_C.new());
	
	
	before():gui_start(){
		GUI_C gui = new GUI_C();
		gui.setTexts(getLang());
		gui.start(gui.getTexts());
		System.out.println(" - English version.");

	}
	
	public String[] getLang(){
		String[] texts = new String[10];
		texts[0] = "Welcome to our chatroom!";
		texts[1] = "log in";
		texts[2] = "exit";
		return texts;
	}
}
