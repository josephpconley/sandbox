package joejava.nfl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.auth.params.AuthPNames;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.AuthPolicy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.message.AuthRequest;
import org.openid4java.discovery.yadis.YadisResolver;
import org.openid4java.discovery.yadis.YadisResult;

public class YahooFFUtil{
    
	public static final String requestLink = "https://api.login.yahoo.com/oauth/v2/get_request_token";
	public static final String accessLink = "https://api.login.yahoo.com/oauth/v2/get_token";
	public static final String authorizeLink = "https://api.login.yahoo.com/oauth/v2/request_auth";
	
	public static final String key = "dj0yJmk9NDVNcGJhckVjTkhXJmQ9WVdrOVEybG9XamxaTnpRbWNHbzlNVE0xTkRFeU5qazJNZy0tJnM9Y29uc3VtZXJzZWNyZXQmeD04Mg--";
	public static final String secret = "b02b5a74970e27f9bded83201e990e5b248fb0e7";
	public static final String appId = "CihZ9Y74";
	
	public static final String mlb = "mlb.l.482246";
	public static final String nfl = "nfl.l.132958";
	
	public static void main(String[] args) throws Exception {

		String openId = "https://me.yahoo.com/a/D2Kwq2YwxoL_3lP0xm.WfEzD1sVS";
    	//appId = CihZ9Y74

    	/*** OPEN ID ***/
    	ConsumerManager manager = new ConsumerManager();
    	
        // perform discovery on the user-supplied identifier
/*    	
    	List discoveries = manager.discover(openId);
        DiscoveryInformation d = (DiscoveryInformation)discoveries.get(0);
        System.out.println(d.getClaimedIdentifier().toString());
        System.out.println(d.getVersion());
  */
        // attempt to associate with the OpenID provider
        // and retrieve one service endpoint for authentication
//        DiscoveryInformation discovered = manager.associate(discoveries);
        
        /*
        DefaultHttpClient openClient = new DefaultHttpClient();
        
        HttpGet openGet = new HttpGet(returnURL);
        HttpParams params = new BasicHttpParams();
        //params.setParameter("openid.assoc_handle", "");
        //params.setParameter("openid.claimed_id", "");
        //params.setParameter("openid.identity", "");
        params.setParameter("openid.mode", "checkid_setup");	//test that
        params.setParameter("openid.ns", "http://specs.openid.net/auth/2.0");
        params.setParameter("openid.realm", "http://yahoo.com");
        params.setParameter("openid.return_to", "http://yahoo.com");
        params.setParameter("openid.ns.oauth", "http://specs.openid.net/extensions/oauth/1.0");
        params.setParameter("openid.oauth.consumer", "--");
        
        openGet.setParams(params);
        HttpResponse openResponse = openClient.execute(openGet);
        
        System.out.println(openResponse.getParams().getParameter("openid.ns.oauth"));
        System.out.println(openResponse.getParams().getParameter("openid.oauth.request_token"));
    	HttpEntity openEntity = openResponse.getEntity();
    	if (openEntity != null) {
    	    InputStream instream = openEntity.getContent();
    		BufferedReader br = new BufferedReader(new InputStreamReader(instream));
    	    String line;

    	    while ((line = br.readLine()) != null) {
    	    	//System.out.println(line);
    	    }
    	    br.close();
    	}
		
		
    	//final String requestLink = "https://fantasysports.yahooapis.com/oauth/request_token";
    	//final String accessLink = "https://fantasysports.yahooapis.com/oauth/access_token";
    	//final String authorizeLink = "https://fantasysports.yahooapis.com/oauth/authorize";
    	
    	//Signpost
    	OAuthConsumer consumer = new CommonsHttpOAuthConsumer(key,secret);
        OAuthProvider provider = new CommonsHttpOAuthProvider(requestLink,accessLink,authorizeLink);
        
        String authUrl = provider.retrieveRequestToken(consumer, OAuth.OUT_OF_BAND);
        
        System.out.println("Request token: " + consumer.getToken());
        System.out.println("Token secret: " + consumer.getTokenSecret());        
        System.out.println(authUrl);

        System.out.println("Enter the verification code and hit ENTER when you're done");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String verifier = br.readLine();
        br.close();
        
		provider.retrieveAccessToken(consumer, verifier);
		System.out.println("Access token: " + consumer.getToken());
        System.out.println("Token secret: " + consumer.getTokenSecret());
		
		//Actual football request
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet("http://fantasysports.yahooapis.com/fantasy/v2/league/" + nfl);
    	consumer.sign(httpget);
     	
    	HttpResponse response = httpclient.execute(httpget);
    	HttpEntity entity = response.getEntity();
    	if (entity != null) {
    	    InputStream instream = entity.getContent();
    		BufferedReader b = new BufferedReader(new InputStreamReader(instream));
    	    String line;

    	    while ((line = b.readLine()) != null) {
    	    	System.out.println(line);
    	    }

    	    b.close();
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
