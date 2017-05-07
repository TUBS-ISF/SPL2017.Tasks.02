package Client.Basic;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

public class testReader {
	public static void main(String[] args) {
		test1();
		new testReader().test2();
		
	}
	
	private void test2() {
		String lang = "DE";
		String fileName = "/res/lang.xml";
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("language");
			for (int i = 0; i < nList.getLength(); i++) {
				Node langNode = nList.item(i);
				if (langNode.getAttributes().item(0).getNodeValue().equals(lang)) {
					System.out.println(doc.getElementsByTagName("login").item(i).getTextContent());
				}
				// System.out.println(langNode.getAttributes().item(0).getNodeValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Loading process error!");
		}
	}

	private static void test1() {
		String lang = "EN";
		try {
			String path = System.getProperty("user.dir");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(path + "/res/lang.xml");
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("language");
			for (int i = 0; i < nList.getLength(); i++) {
				Node langNode = nList.item(i);
				if (langNode.getAttributes().item(0).getNodeValue().equals(lang)) {
					System.out.println(doc.getElementsByTagName("login").item(i).getTextContent());
				}
				// System.out.println(langNode.getAttributes().item(0).getNodeValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Loading process error!");
		}
	}

}
