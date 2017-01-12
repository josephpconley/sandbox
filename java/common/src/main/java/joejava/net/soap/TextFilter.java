package joejava.soap;

import javax.xml.soap.*;

import java.io.*;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
/*
import com.cdyne.ws.profanityWS.profanityAsmx.SimpleProfanityFilterDocument;
import com.cdyne.ws.profanityWS.profanityAsmx.SimpleProfanityFilterResponseDocument;
import com.cdyne.ws.profanityWS.profanityAsmx.SimpleProfanityFilterDocument.SimpleProfanityFilter;
import com.cdyne.ws.profanityWS.profanityAsmx.SimpleProfanityFilterResponseDocument.SimpleProfanityFilterResponse;
*/
/** 
* This class handles the input of a message, sends it to the Profanity Web Service, 
* saves the return filtered message in a DataTable (via a MessageSet), and provides an interface 
* to query this DataTable. 
*
* @author Joe Conley
*/
public class TextFilter {

	/**
	 *	Empty constructor
	 */

	static String url = "http://ws.cdyne.com/ProfanityWS/Profanity.asmx?wsdl";
	
	public TextFilter(){}
	/*
	public static void main(String args[]) throws Exception {
  		TextFilter obj = new TextFilter(); 	
		String replyText=null;
		String origText="";
		BufferedReader bin = new BufferedReader(new InputStreamReader(System.in));
		DataTable data = new DataTable();
		MessageSet set = new MessageSet();
		
		System.out.println("Please enter a string to be censored, followed by the \"Enter\" key:");
			
		while(origText.equals("exit")==false){
			origText=bin.readLine();
	
			if(QueryHandler.isCommand(origText.substring(0,4))==false){							
				try{
					replyText=obj.getResponse(obj.makeCall(
												obj.getRequest(origText)));
					set.add(origText,replyText,data);
				}
				catch(SOAPException e){
					System.out.println(e);
				}
			}
			else if(origText.substring(0,4).equals("stat")){
				//pass a query to the DataTable for running statistics
				System.out.println(QueryHandler.executeQuery(origText,data));
			}
		}
	}
	
   public XmlObject getRequest(String origText){
		SimpleProfanityFilterDocument spfDoc = SimpleProfanityFilterDocument.
			Factory.newInstance();
		SimpleProfanityFilter spf = spfDoc.addNewSimpleProfanityFilter();
		spf.setText(origText);
		return spfDoc;
   }
   
   public Node makeCall(XmlObject request) throws SOAPException{
	   //Create the actual message
	   MessageFactory messageFactory = MessageFactory.newInstance();
	   SOAPMessage message = messageFactory.createMessage();
	  
	   //Create objects for the message parts            
	   SOAPPart soapPart = message.getSOAPPart();
	   SOAPEnvelope envelope = soapPart.getEnvelope();
	   SOAPBody body = envelope.getBody();
		
	   //Create request
	   Document doc = (Document)request.newDomNode();
	   body.addDocument(doc);
		
	   message.saveChanges();
			
	   //Create the connection
	   SOAPConnectionFactory soapConnFactory = 
	                    SOAPConnectionFactory.newInstance();
	   SOAPConnection connection = 
	                    soapConnFactory.createConnection();
	   SOAPMessage reply = connection.call(message, url);
	   SOAPBody response = reply.getSOAPBody();	
	
	   connection.close();
 	  	  
	   return response.getFirstChild();   
   }

   public String getResponse(Node n){
		String replyText=null;
		
		try {
			SimpleProfanityFilterResponseDocument retDoc =
			SimpleProfanityFilterResponseDocument.Factory.parse(n);
			SimpleProfanityFilterResponse resp = 
				retDoc.getSimpleProfanityFilterResponse();
			
			replyText = resp.getSimpleProfanityFilterResult().getCleanText();
			System.out.println(replyText);

		} 
		catch (XmlException e) {
			e.printStackTrace();
		}
	
		return replyText;
	}
   */
}