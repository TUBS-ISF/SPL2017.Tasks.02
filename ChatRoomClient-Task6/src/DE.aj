
public aspect DE {

	pointcut gui_start(): execution(void GUI_C.start());

	before():gui_start(){
		GUI_C gui = new GUI_C();
		gui.setTexts(getLang());
		gui.start(gui.getTexts());
		System.out.println(" - German version.");
	}

	public String[] getLang() {
		String[] texts = new String[10];
		texts[0] = "Willkommen in unserem Chatraum!";
		texts[1] = "anmelden";
		texts[2] = "beenden";
		return texts;
	}
}
