package joejava.google;

import java.net.URL;

import com.google.gdata.client.Query;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.calendar.CalendarEventFeed;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.extensions.FamilyName;
import com.google.gdata.data.extensions.Name;

public class CalendarTest {
	public static void main(String[] args) throws Exception {
		//getOAuthInfo();
		//getLeagueInfo("earhybc", "978217f3bb895299044e662ed1de235ca74f5aac", "szzezg");

		CalendarService myService = new CalendarService("Joe");
        myService.setUserCredentials("josephpconley@gmail.com", "theone08");
            
        URL feedUrl = new URL("http://www.google.com/calendar/feeds/josephpconley@gmail.com/private/full");
        CalendarEventFeed resultFeed = myService.getFeed(feedUrl, CalendarEventFeed.class);
        
        for (int i = 0; i < resultFeed.getEntries().size(); i++) {
        	CalendarEventEntry entry = resultFeed.getEntries().get(i);
        }
        
    }	
}
