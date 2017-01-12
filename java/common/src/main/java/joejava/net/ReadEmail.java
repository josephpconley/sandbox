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

public class ReadEmail {
	
	public static void main (String[] args) throws IOException, MessagingException{
		
		Properties props = System.getProperties();
	    props.put("mail.store.protocol", "imaps");
	    
	    Session session = Session.getInstance(props, null);
	    Store store = session.getStore("imaps");
	    
	    try {
	    	String host = "imap.gmail.com";
	    	store.connect(host,"josephpconley@gmail.com","arvydas11");
	    	//System.out.println(store);
	    	
	    	Folder inbox = store.getFolder("Inbox");
	        inbox.open(Folder.READ_WRITE);
	    	
	        Message message[] = inbox.getMessages();	        
	        System.out.println(message[6].getContentType());
	        
	        //if message.getContent() instanceof Multipart
	        //Multipart mp = (Multipart)message[6].getContent();
	        //String body = (String)mp.getBodyPart(0).getContent();
	        
	        //if plain text
	        String body =(String)message[6].getContent();
	        body += " to the house";
	        
	        
	        
	    } catch (MessagingException mex) {
	        System.out.println("send failed, exception: " + mex);
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
