import interfaces.ISetLanguage;

public class EN implements ISetLanguage {

	@Override
	public String setLang() {
		return "EN";
	}

	@Override
	public String[] setTexts() {
		String[] texts = new String[10];
		texts[0] = "Welcome to our chatroom!";
		texts[1] = "log in";
		texts[2] = "exit";
		return texts;
	}

}
