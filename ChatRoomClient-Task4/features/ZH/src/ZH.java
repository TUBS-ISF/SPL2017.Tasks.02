import interfaces.ISetLanguage;

public class ZH implements ISetLanguage{

	@Override
	public String setLang() {
		return "ZH";
	}

	@Override
	public String[] setTexts() {
		String[] texts = new String[10];
		texts[0] = "欢迎来到我们的聊天室！";
		texts[1] = "登陆";
		texts[2] = "退出";
		return texts;
	}

}
