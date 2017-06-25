
public aspect ZH {

	pointcut gui_start(): execution(void GUI_C.start());

	before():gui_start(){
		GUI_C gui = new GUI_C();
		gui.setTexts(getLang());
		gui.start(gui.getTexts());
		System.out.println(" - Chinese version.");
	}

	public String[] getLang() {
		String[] texts = new String[10];
		texts[0] = "欢迎来到我们的聊天室！";
		texts[1] = "登陆";
		texts[2] = "退出";
		return texts;
	}
}