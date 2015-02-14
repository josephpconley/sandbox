package joejava.mathoms;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

public class SendEmail {
	
	public static void main (String[] args) throws IOException, MessagingException{
		
		Properties props = System.getProperties();
	    props.put("mail.smtp.auth", "true");
	    
	    Session session = Session.getInstance(props, null);
	    Transport t = null;
	    
	    try {
	    	String host = "smtp.gmail.com";
	    	t = session.getTransport("smtps");
	    	
	        Message msg = new MimeMessage(session);
	        msg.setFrom();

	        InternetAddress[] recipients = new InternetAddress[1];
	        	recipients[0]= new InternetAddress("6103894127@vtext.net");
	        msg.setRecipients(Message.RecipientType.TO,recipients);
	        msg.setSubject("Text from Java program");
	        
	        //Write text components of email
	        MimeBodyPart mbp1 = new MimeBodyPart();
	    	String subject = msg.getSubject();
	    	StringBuffer sb = new StringBuffer();
	    	sb.append("<html>\n");
	    	sb.append("<head>\n");
	    	sb.append("<title>\n");
	    	sb.append(subject + "\n");
	    	sb.append("</title>\n");
	    	sb.append("</head>\n");

	    	sb.append("<body>\n");
	    	sb.append("<H3>Good morning!  Hope I didn't wake you, just wanted to try out a new Java program that sends emails.  Taxi!  Touchdown!</H3>" + "\n");	    	

	    	sb.append("</body>\n");
	    	sb.append("</html>\n");

	    	mbp1.setDataHandler(new DataHandler(
	    		new ByteArrayDataSource(sb.toString(), "text/html")));
	        
	        Multipart mp = new MimeMultipart();
	        mp.addBodyPart(mbp1);
	        
	        msg.setContent(mp);
	        msg.setSentDate(new Date());
	       
	        t.connect(host,"josephpconley@gmail.com","arvydas11");
	        t.sendMessage(msg,msg.getAllRecipients());
	        
	        //Save to sent folder
	        Store store = session.getStore("imaps");
	        Folder sent = store.getFolder("[Gmail]/Sent Mail");
	        sent.open(Folder.READ_WRITE);
	        
	        Message[] messages = new Message[0];
	        messages[0]=msg;
	        sent.appendMessages(messages);
	    } catch (MessagingException mex) {
	        System.out.println("send failed, exception: " + mex);
	    }finally{
	    	t.close();
	    }
	}
	
	/*Cell phone specs
	 * 
	 * AT&T ##########@txt.att.net
	 * 
	 * Verizon ##########@vtext.com
	 * 
	 * 
	 */
}
