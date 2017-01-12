package joejava.mathoms;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

public class EditEmail {
	
	public static void main (String[] args) throws IOException, MessagingException{
		
		Properties props = System.getProperties();
	    props.put("mail.store.protocol", "imaps");
	    
	    Session session = Session.getInstance(props, null);
	    Store store = session.getStore("imaps");
	    
	    try {
	    	String host = "imap.gmail.com";
	    	store.connect(host,"josephpconley@gmail.com","theone08");
	    	//System.out.println(store);
	    	
	    	Folder inbox = store.getFolder("Inbox");
	        inbox.open(Folder.READ_WRITE);
	    	
	        Message message[] = inbox.getMessages();	        
	        System.out.println(message[0].getContentType());
	        
	        //if message.getContent() instanceof Multipart
	        //Multipart mp = (Multipart)message[6].getContent();
	        //String body = (String)mp.getBodyPart(0).getContent();
	        
	        //if plain text
	        String body =(String)message[0].getContent();
	        body += "to the house";
	        
	        Message newMsg = new MimeMessage(session);
	        newMsg.setText(body);
	        
	        Message[] messages = new Message[1];
	        messages[0] = newMsg;
	        System.out.println((String)messages[0].getContent());
	        inbox.appendMessages(messages);
	        
	    } catch (MessagingException mex) {
	        System.out.println("send failed, exception: " + mex);
	        mex.printStackTrace();
	    }finally{
	    	store.close();
	    }
	}
	
	/*Cell phone specs
	 * 
	 * AT&T ##########@txt.att.net
	 * 
	 * Verizon ##########@vtext.net
	 * 
	 * 
	 */
}
