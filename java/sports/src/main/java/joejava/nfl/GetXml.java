package joejava.nfl;

import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

public class GetXml {

	public static void main (String[] args) {
	  
		try{
			//URLConnection link = new URL("http://www.nfl.com//liveupdate/scorestrip/ss.xml").openConnection();
			//link.connect();

			URL feed = new URL("http://www.nfl.com//liveupdate/scorestrip/ss.xml");
			
			Document dom;
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();

			//parse using builder to get DOM representation of the XML file
			dom = db.parse(feed.toString());
			
			//get the root element
			Element root = dom.getDocumentElement();
				
			//get a nodelist of  elements
			NodeList nl = (NodeList) root.getElementsByTagName("g");
			
			for(int i = 0 ; i < nl.getLength();i++) {
				Element el = (Element)nl.item(i);
				System.out.println(el.getAttribute("h") + " "+el.getAttribute("hs") + " " +
						el.getAttribute("v") + " " + el.getAttribute("vs"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

