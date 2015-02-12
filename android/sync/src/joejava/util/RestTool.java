package joejava.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class RestTool {
	
	public static final String LOCALHOST = "http://10.0.2.2:9000/feed/";
	public static final String HOST = "http://joecdev.no-ip.org/feed/";
	public static final String NOTES_URL = HOST + "notes/";
	public static final String TAGS_URL = HOST + "tags/";
	public static final String SMS_URL = HOST + "sms/";
	
	public static void post(String url, String json) {
		HttpClient httpclient = new DefaultHttpClient();
	    HttpPost post = new HttpPost(url);
	    HttpResponse response;
	    try {
	    	post.setEntity(new StringEntity(json));
	        response = httpclient.execute(post);
	        Log.i("log",response.getStatusLine().toString());
	    } catch (ClientProtocolException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }		
	}	
	
	public static String get(String url){
		HttpClient httpclient = new DefaultHttpClient();
	    HttpGet httpget = new HttpGet(url); 
	    HttpResponse response;
	    String result = null;
	    try {
	        response = httpclient.execute(httpget);
	        HttpEntity entity = response.getEntity();
	        // If the response does not enclose an entity, there is no need
	        // to worry about connection release
	 
            if (entity != null) {

            	InputStream instream = entity.getContent();
	            result = convertStreamToString(instream);
	            
	            /*
	            JSONObject json=new JSONObject(result);
	            Log.i("log","<jsonobject>\n"+json.toString()+"\n</jsonobject>");
	         	*/
                instream.close();
            }
            
	    } catch (ClientProtocolException e) {
	            // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    
	    return result;
	}
	    
    private static String convertStreamToString(InputStream is) {
    	 
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
 
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
