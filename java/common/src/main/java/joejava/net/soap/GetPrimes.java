package joejava.soap;

import javax.xml.soap.*;
import java.io.*;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/*
import com.microsoft.webservices.GetPrimeNumbersDocument;
import com.microsoft.webservices.GetPrimeNumbersResponseDocument;
import com.microsoft.webservices.GetPrimeNumbersDocument.GetPrimeNumbers;
import com.microsoft.webservices.GetPrimeNumbersResponseDocument.GetPrimeNumbersResponse;
*/
/** 
* 
*
* @author Joe Conley
*/
public class GetPrimes {

	static String url = "http://www50.brinkster.com/vbfacileinpt/np.asmx";
	
	public GetPrimes(){}

	/*
	public static void main(String args[]) throws Exception {
  		GetPrimes obj = new GetPrimes(); 	
		int input = 99;
		BufferedReader bin = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Enter a max, followed by the \"Enter\" key:");
			
		while(input > 0){
			input=bin.read();
			
			try{
			obj.getPrimes(obj.makeCall(obj.getRequest(input)));
			}
			catch(SOAPException e){
				System.out.println(e);
			}
		}
	}
	
   public XmlObject getRequest(int input){
	   
		GetPrimeNumbersDocument doc = GetPrimeNumbersDocument.Factory.newInstance();
		GetPrimeNumbers primes = doc.addNewGetPrimeNumbers();
		primes.setMax(input);
		System.out.println(doc.toString());
		
		return doc;
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
	  
	  //System.out.println(response.getFirstChild().getClass().toString());
 	  	  
	  return response.getFirstChild();   
   }

   public void getPrimes(Node n){
	   
		try {
			GetPrimeNumbersResponseDocument retDoc = 
			GetPrimeNumbersResponseDocument.Factory.parse(n);
			GetPrimeNumbersResponse resp = 
				retDoc.getGetPrimeNumbersResponse();
			
			System.out.println("Primes: "+resp.getGetPrimeNumbersResult());
		} 
		catch (XmlException e) {
			e.printStackTrace();
		}		
	}
	*/
}
