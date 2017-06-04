import java.util.List;

import interfaces.ISetLanguage;
import interfaces.ISetOS;
import interfaces.IStartUI;
import loader.PluginLoader;

public class Main {
	public static void main(String[] args) {
		// First, show which OS is selected
		List<ISetOS> osList = PluginLoader.load(ISetOS.class);
		for (ISetOS os : osList) {
			os.showOS();
			// System.out.println(ol.getClass().getName());
		}
		// Second, get UI
		// In this step, language must be set

		List<IStartUI> uiList = PluginLoader.load(IStartUI.class);
		for (IStartUI ui : uiList) {
			String[] texts = {""};
			// For command-line only english is supported currently
			List<ISetLanguage> langList = PluginLoader.load(ISetLanguage.class);
			if (langList.size() > 0) {
				// one language can be selected, we do not need iteration
				ISetLanguage lang = langList.get(0);
				texts = lang.setTexts();
			}
			ui.start(texts);
		}

		// Third, enable functions
		// Skip due to lack of time
	}
}
