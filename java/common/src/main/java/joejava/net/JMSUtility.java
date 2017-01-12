/**
 * 
 */
package joejava.util;

import java.io.IOException;
import java.util.Properties;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @author jconley
 *
 */
public class JMSUtility {
	
	public final static String CONTEXT_FACTORY = "javax.naming.InitialContext";
	public final static String PROVIDER_URL = "stcms://xdevint4:25007";
	public final static String JMS_USERNAME = "seebdev";
	public final static String JMS_PASSWORD = "seebdev";
	
	public static InitialContext ic = null;
	static QueueConnectionFactory connFact = null;
	static QueueConnection connection = null;
	static Session session = null;
	static Destination destination = null;
	static MessageProducer producer = null;
	static int totFiles;
	static long totSize;
	
    public static boolean put(String text, String queue){
        //
        Properties properties = new Properties();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, CONTEXT_FACTORY);
        properties.put(Context.PROVIDER_URL, PROVIDER_URL);
        properties.put(Context.SECURITY_PRINCIPAL, JMS_USERNAME);
        properties.put(Context.SECURITY_CREDENTIALS, JMS_PASSWORD);
        
        for(Object prop : properties.values()){
        	System.out.println(prop.toString());
        }
        
        for(Object prop : properties.keySet()){
        	System.out.println(prop.toString());
        }        
        
        try {
			ic = new InitialContext(properties);
			connFact = (QueueConnectionFactory) ic.lookup("connectionfactories/queueconnectionfactory");
		} catch (NamingException e) {
			e.printStackTrace();
		}
        
		TextMessage msg = null;
        try {
			connection = connFact.createQueueConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
	        destination = (Destination) ic.lookup("queues/" + queue);
	        producer = session.createProducer(destination);
	        
	        msg = session.createTextMessage();
	        msg.setText(text);
	        
	        System.out.println(msg.getText() + " " + queue);
        	producer.send(msg);
        	System.out.println("success");
        	
        	producer.close();
	        session.close();
	        connection.close();
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
        
        return true;
    }

    public static void main(String[] args) throws NamingException, JMSException, IOException {
        put("12345","qManifestOut");
    }

}

