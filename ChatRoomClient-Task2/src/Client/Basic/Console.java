package Client.Basic;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * I'm not really sure if this is needed, reserved.
 * 
 * @author Longxin Li
 *
 */
public class Console {

	private String[] texts;

	public Console(String lang) {
		new Console(null).fillTexts(null);
	}

	/**
	 * All text should be loaded from file "lang.xml" in folder "res". This is
	 * the same function as in {@link Client.GUI.ClientGUI}.
	 * 
	 * @param lang
	 *            chosen language
	 */
	private void fillTexts(String lang) {
		String file = "/res/lang.xml";
		// default size is 5
		texts = new String[5];
		try {
			String path = System.getProperty("user.dir");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(path + file);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("language");
			for (int i = 0; i < nList.getLength(); i++) {
				Node langNode = nList.item(i);
				if (langNode.getAttributes().item(0).getNodeValue().equals(lang)) {
					texts[0] = doc.getElementsByTagName("welcome").item(i).getTextContent();
					texts[1] = doc.getElementsByTagName("login").item(i).getTextContent();
					// etc.
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Loading process error!");
		}
	}
}
