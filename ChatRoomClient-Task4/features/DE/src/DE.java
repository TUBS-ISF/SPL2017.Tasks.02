import interfaces.ISetLanguage;

public class DE implements ISetLanguage {

	@Override
	public String setLang() {
		return "DE";
	}

	@Override
	public String[] setTexts() {
		String[] texts = new String[10];
		texts[0] = "Willkommen in unserem Chatraum!";
		texts[1] = "anmelden";
		texts[2] = "beenden";
		return texts;
	}

}
