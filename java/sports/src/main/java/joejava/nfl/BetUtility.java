package joejava.nfl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.auth.params.AuthPNames;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.AuthPolicy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.message.AuthRequest;

public class BetUtility{
    public static void main(String[] args) throws Exception {

    	String apiKey = "d8b95fbeb687490167b2fe57aaaae081ea3f8dc5";
    	
    	DefaultHttpClient httpclient = new DefaultHttpClient();
    	OAuthConsumer consumer = new CommonsHttpOAuthConsumer("dj0yJmk9N2JKeUhQUFhNNVh6JmQ9WVdrOVEybG9XamxaTnpRbWNHbzlNVE0xTkRFeU5qazJNZy0tJnM9Y29uc3VtZXJzZWNyZXQmeD05OA--", 
        													  "744f4cd6a6668c0e33b3466d176b5a2dcd07753f");
        OAuthProvider provider = new DefaultOAuthProvider("https://fantasysports.yahooapis.com/oauth/request_token",
                											"https://fantasysports.yahooapis.com/oauth/access_token",
                											"https://fantasysports.yahooapis.com/oauth/authorize");
        
        //String authUrl = provider.retrieveRequestToken(consumer, OAuth.OUT_OF_BAND);
    	//provider.retrieveAccessToken(consumer, "D2Kwq2YwxoL_3lP0xm.WfEzD1sVS");
        
        HttpGet httpget = new HttpGet("http://fantasysports.yahooapis.com/fantasy/v2/league/mlb.l.482246");
    	consumer.sign(httpget);
     	
    	HttpResponse response = httpclient.execute(httpget);
    	HttpEntity entity = response.getEntity();
    	if (entity != null) {
    	    InputStream instream = entity.getContent();
    		BufferedReader br = new BufferedReader(new InputStreamReader(instream));
    	    String line;

    	    while ((line = br.readLine()) != null) {
    	    	System.out.println(line);
    	    }

    	    br.close();
    	}
    	
    	/*
    	CalendarService myService = new CalendarService("Joe");
        myService.setUserCredentials("josephpconley@gmail.com", "arvydas11");
            
        URL feedUrl = new URL("http://www.google.com/calendar/feeds/josephpconley@gmail.com/private/full");
        CalendarEventFeed resultFeed = myService.getFeed(feedUrl, CalendarEventFeed.class);
            
        System.out.println("Your events:");
        System.out.println();
        
        for (int i = 0; i < resultFeed.getEntries().size(); i++) {
			  CalendarEventEntry entry = resultFeed.getEntries().get(i);
			  System.out.println("\t" + entry.getTitle().getPlainText() + " " + entry.getTimes().get(0).getStartTime().toString());
        }
        */
    }	
}
