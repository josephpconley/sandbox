package joejava.soap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
/*
import net.webserviceX.GetQuoteDocument;
import net.webserviceX.GetQuoteResponseDocument;
import net.webserviceX.GetQuoteDocument.GetQuote;
import net.webserviceX.GetQuoteResponseDocument.GetQuoteResponse;
import noNamespace.StockQuotesDocument;
*/
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;


/** 
* Returns stock quote 
*
* @author Joe Conley
*/
public class GetStock {

	static String url = "http://www.webservicex.net/stockquote.asmx?WSDL";
	
	public GetStock(){}
	/*
	public static void main(String args[]) throws Exception {
  		GetStock obj = new GetStock(); 	
		String input="";
		BufferedReader bin = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Enter a stock symbol:");
			
		while(!input.equals("exit")){
			input=bin.readLine();
			
			try{
				obj.getQuote(obj.makeCall(obj.getRequest(input)));
			}
			catch(SOAPException e){
				System.out.println(e);
			}
		}
	}
	
   public XmlObject getRequest(String input){
		GetQuoteDocument doc = GetQuoteDocument.Factory.newInstance();
		GetQuote quote = doc.addNewGetQuote();
		quote.setSymbol(input);
		
		return doc;
   }
   
   public Node makeCall(XmlObject request) throws SOAPException, TransformerException, IOException{
	   MessageFactory messageFactory = MessageFactory.newInstance();
	   SOAPMessage message = messageFactory.createMessage();
	  
	   SOAPPart soapPart = message.getSOAPPart();
	   SOAPEnvelope envelope = soapPart.getEnvelope();
	   SOAPBody body = envelope.getBody();
		
	   Document doc = (Document)request.newDomNode();
	   body.addDocument(doc);	   
	   message.saveChanges();
	   
	   SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory.newInstance();
	   SOAPConnection connection = soapConnFactory.createConnection();
	   SOAPMessage reply = connection.call(message, url);
	   
	   SOAPBody response = reply.getSOAPBody();	
	   connection.close();
	   message.writeTo(System.out);
 	  	  
	   return response.getFirstChild();   
   }

   public void getQuote(Node node) throws SAXException, IOException, TransformerException{	   
		try {
			GetQuoteResponseDocument retDoc = 
			GetQuoteResponseDocument.Factory.parse(node);
			GetQuoteResponse resp = 
				retDoc.getGetQuoteResponse();
			
			//At this point, this specific web service returns an entire XML as string, must unmarshall
			//	solution parser
			DOMParser parser = new DOMParser();
			parser.parse(new InputSource(new java.io.StringReader(resp.getGetQuoteResult())));
			Document result = parser.getDocument();
			
			//Print xml document
			javax.xml.transform.TransformerFactory tfactory = TransformerFactory.newInstance();
			javax.xml.transform.Transformer xform = tfactory.newTransformer();
			javax.xml.transform.Source src = new DOMSource(result);
			
			java.io.StringWriter writer = new StringWriter();
			StreamResult res = new javax.xml.transform.stream.StreamResult(writer);
			xform.transform(src, res );
			System.out.println(writer.toString());
			
			StockQuotesDocument doc = StockQuotesDocument.Factory.parse(result.getChildNodes().item(0));
			StockBean stock = new StockBean(doc.getStockQuotes().getStock());
			//System.out.println(StringUtility.printBean(stock));
			/*
			NodeList stockNode = stock.getChildNodes();
			for(int i=0;i<stockNode.getLength();i++){
				System.out.println(stockNode.item(i).getNodeName()+": "+
						stockNode.item(i).getTextContent());
			}
		} 
		catch (XmlException e) {
			e.printStackTrace();
		}		
	}
	*/
}