package joejava.xfire;

import java.net.MalformedURLException;
import java.sql.SQLException;

import joejava.util.StringUtility;
import joejava.xfire.Contact;

import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.codehaus.xfire.transport.http.CommonsHttpMessageSender;

/**
 */
public class ContactClient
{
    public static void main(String args[]) throws SQLException
    {
        String serviceURL = "http://localhost:81/pensieve/services/ContactService";
        Service serviceModel = new ObjectServiceFactory().create(ContactService.class,null,"http://xfire.codehaus.org/ContactService",null);
        
        XFireProxyFactory serviceFactory = new XFireProxyFactory();
        
        try
        {
            ContactService service = (ContactService) serviceFactory.create(serviceModel, serviceURL);
            Client client = Client.getInstance(service);
            // disable timeout
            client.setProperty(CommonsHttpMessageSender.HTTP_TIMEOUT, "0");

            Contact me = service.getContact("Joe Conley");
            System.out.println(StringUtility.printBean(me));
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        } 
    }
}

