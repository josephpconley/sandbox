package joejava.soap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.TransformerException;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/*
import com.aonaware.services.webservices.ArrayOfDefinition;
import com.aonaware.services.webservices.DefineInDictDocument;
import com.aonaware.services.webservices.DefineInDictResponseDocument;
import com.aonaware.services.webservices.Definition;
import com.aonaware.services.webservices.WordDefinition;
import com.aonaware.services.webservices.DefineInDictDocument.DefineInDict;
import com.aonaware.services.webservices.DefineInDictResponseDocument.DefineInDictResponse;
*/

/** 
* Returns definition from web service
*
* @author Joe Conley
*/
public class GetDefinition {

	static String url = "http://services.aonaware.com/DictService/DictService.asmx?WSDL";
	
	public GetDefinition(){}
/*	
	public static void main(String args[]) throws Exception {
  		GetDefinition obj = new GetDefinition(); 	
		String input="";
		BufferedReader bin = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Enter a word:");
			
		while(!input.equals("exit")){
			input=bin.readLine();
			
			try{
				obj.getDefinition(obj.makeCall(obj.getRequest(input)));
			}
			catch(SOAPException e){
				System.out.println(e);
			}
		}
	}
	
   public XmlObject getRequest(String input){
		DefineInDictDocument doc = DefineInDictDocument.Factory.newInstance();
		DefineInDict query = doc.addNewDefineInDict();
		query.setWord(input);
		query.setDictId("*");  //See comment below for proper arguments
		
		System.out.println(doc.toString());
		
		return doc;
   }
   
   public Node makeCall(XmlObject request) throws SOAPException, IOException{
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
	   //message.writeTo(System.out);
 	  	  
	   return response.getFirstChild();   
   }

   public void getDefinition(Node node) throws SAXException, IOException{	   
		try {
			System.out.println(node.toString());
			DefineInDictResponseDocument retDoc = DefineInDictResponseDocument.Factory.parse(node);
			DefineInDictResponse resp = 
				retDoc.getDefineInDictResponse();

			WordDefinition word = resp.getDefineInDictResult();
			ArrayOfDefinition defs = word.getDefinitions();
			for(int i=0;i<defs.sizeOfDefinitionArray();i++){
				System.out.println(defs.getDefinitionArray(i).getWordDefinition());
			}
			
			
		} 
		catch (XmlException e) {
			e.printStackTrace();
		}		
	}
   
   /*
    * 		<option value="*">Any</option>
		<option value="bouvier">Bouvier's Law Dictionary, Revised 6th Ed (1856)</option>
		<option value="world02">CIA World Factbook 2002</option>
		<option value="easton">Easton's 1897 Bible Dictionary</option>
		<option value="elements">Elements database 20001107</option>

		<option value="hitchcock">Hitchcock's Bible Names Dictionary (late 1800's)</option>
		<option value="jargon">Jargon File (4.3.1, 29 Jun 2001)</option>
		<option value="moby-thes">Moby Thesaurus II by Grady Ward, 1.0</option>
		<option value="gcide">The Collaborative International Dictionary of English v.0.48</option>
		<option value="devils">THE DEVIL'S DICTIONARY ((C)1911 Released April 15 1993)</option>
		<option value="foldoc">The Free On-line Dictionary of Computing (27 SEP 03)</option>

		<option value="gazetteer">U.S. Gazetteer (1990)</option>
		<option value="gaz-county">U.S. Gazetteer Counties (2000)</option>
		<option value="gaz-place">U.S. Gazetteer Places (2000)</option>
		<option value="gaz-zip">U.S. Gazetteer Zip Code Tabulation Areas (2000)</option>
		<option value="vera">Virtual Entity of Relevant Acronyms (Version 1.9, June 2002)</option>
		<option value="wn">WordNet (r) 2.0</option>

    */
}