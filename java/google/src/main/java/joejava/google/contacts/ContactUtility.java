package joejava.google.contacts;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.google.gdata.client.Query;
import com.google.gdata.client.Service.GDataRequest;
import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.Link;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.extensions.Name;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ContentType;
import com.google.gdata.util.ServiceException;

public class ContactUtility {
	
	private static final ContactsService contactService = new ContactsService("Joe");
	private static URL contactUrl = null;
	
	public static void main(String[] args) throws Exception{
		//List<ContactEntry> entries = getMyContacts(null);
		ContactEntry joe = getContact("5165814579", null);
		System.out.println(joe.getName().getFullName().getValue());
	}

	static{
        try {
			contactService.setUserCredentials("josephpconley@gmail.com", "theone08");
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
        
		try {
			contactUrl = new URL("https://www.google.com/m8/feeds/contacts/default/full");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
        
	}
	
	public static void getContactBySelf() throws Exception{
        Query contactQuery = new Query(contactUrl);
        contactQuery.setMaxResults(1000);
		ContactFeed contactFeed = contactService.query(contactQuery, ContactFeed.class);
		for(ContactEntry e : contactFeed.getEntries()){
			System.out.print(e.getName().getFullName() + " ");
		}
	}
	
	public static List<ContactEntry> getMyContacts(String q) throws IOException, ServiceException{
		Query contactQuery = new Query(contactUrl);
		if(q != null){ contactQuery.setFullTextQuery(q);}
		
        //I only care about Contacts added to My Contacts
        contactQuery.setStringCustomParameter("group", "http://www.google.com/m8/feeds/groups/josephpconley%40gmail.com/base/6");	
        contactQuery.setMaxResults(1000);
        
        ContactFeed contactFeed = contactService.query(contactQuery, ContactFeed.class);
        return contactFeed.getEntries();
	}
	
	public static ContactEntry getContact(String q){
		return getContact(q, contactUrl);
	}
	
	public static ContactEntry getContact(String q, URL url){
        url = (url == null) ? contactUrl : url;
		
		Query contactQuery = new Query(url);
		if(q != null){ contactQuery.setFullTextQuery(q);}
		
        //I only care about Contacts added to My Contacts
        contactQuery.setStringCustomParameter("group", "http://www.google.com/m8/feeds/groups/josephpconley%40gmail.com/base/6");	
        
        try{
            ContactFeed contactFeed = contactService.query(contactQuery, ContactFeed.class);
            
            if(contactFeed.getEntries().size() > 1){
            	throw new Exception("Too many contacts (" + contactFeed.getEntries().size() + ") returned from query: " + q);
            }
            
            if(contactFeed.getEntries().size() == 0){
            	System.out.println("No contact found");
            	return null;
            }
            
            return contactFeed.getEntries().get(0);
        }catch(Exception e){
        	System.out.println("No contact found " + e.getMessage());
        }
        
        return null;
	}
	
	
	public static List<ContactEntry> getContacts(String q) throws Exception{
        URL url = contactUrl;
		
		Query contactQuery = new Query(url);
		if(q != null){ contactQuery.setFullTextQuery(q);}
		
        //I only care about Contacts added to My Contacts
        contactQuery.setStringCustomParameter("group", "http://www.google.com/m8/feeds/groups/josephpconley%40gmail.com/base/6");	
        return contactService.query(contactQuery, ContactFeed.class).getEntries();
	}	
	
	public static byte[] getContactPhoto(ContactEntry entry) throws Exception{
		  Link photoLink = entry.getContactPhotoLink();
		  byte[] photo = new byte[4096];
		  
		  if(photoLink != null && photoLink.getEtag() != null) {
			  GDataRequest request = contactService.createLinkQueryRequest(photoLink);
			  request.execute();
			  
			  InputStream in = request.getResponseStream();
			  ByteArrayOutputStream out = new ByteArrayOutputStream();
			  
			  for (int read = 0; (read = in.read(photo)) != -1;out.write(photo, 0, read));
			  request.end();
			  
			  return out.toByteArray();
		  }else{
			  System.out.println("Photo invalid/not present");
			  return null;
		  }
	}
	
	//Only will insert a photo (updates don't work yet)
	public static void addContactPhoto(ContactEntry entry, ContactsService service, byte[] photoData) throws ServiceException, IOException {
		Link photoLink = entry.getContactPhotoLink();

		URL photoUrl = new URL(photoLink.getHref());
		System.out.println(entry.getName().getFullName().getValue() + " " + photoLink.getEtag());
		
		GDataRequest request = service.createRequest(GDataRequest.RequestType.UPDATE, photoUrl, new ContentType("image/jpeg"));
		request.setEtag(photoLink.getEtag());
		
		OutputStream requestStream = request.getRequestStream();
		requestStream.write(photoData);
		request.execute();
	}


	public static void update(ContactEntry e) throws IOException, ServiceException{
		contactService.update(contactUrl, e);
	}

	public static void insertContact(URL url, ContactEntry entry) throws IOException, ServiceException{
        contactService.insert(url, entry);
	}
	
	public static void printAllContacts(ContactsService service, URL url, ContactFeed feed){
        System.out.println(feed.getTitle().getPlainText());
        
        for(int i=0;i<feed.getEntries().size();i++){
        	ContactEntry entry = feed.getEntries().get(i);
        	Name name = entry.getName();
        	if(name != null){
        		System.out.println(name.getFullName().getValue());
        	}
        }
	}	
}
