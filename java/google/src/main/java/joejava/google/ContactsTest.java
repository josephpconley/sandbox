package joejava.google;

import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URL;

import joejava.google.contacts.ContactUtility;

import com.google.gdata.client.Service.GDataRequest;
import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.Link;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.extensions.Name;
import com.google.gdata.util.ContentType;
import com.google.gdata.util.ServiceException;

public class ContactsTest {

	public static void main(String[] args) throws Exception {
        
		ContactEntry joe = ContactUtility.getContact("Dave Stortini", null);
		System.out.println(joe.getName().getFullName().toString());
		
		RandomAccessFile file = new RandomAccessFile(joe.getName().getFullName().getValue()+".jpeg", "rw");
		file.write(ContactUtility.getContactPhoto(joe));
		file.close();
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



	public static void insertContact(ContactsService service, URL url, ContactEntry entry) throws IOException, ServiceException{
        service.insert(url, entry);			
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
