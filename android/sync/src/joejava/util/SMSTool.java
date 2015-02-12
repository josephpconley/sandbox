package joejava.util;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class SMSTool {
	public static String toMap(String from, String to, long timestamp, String body){
    	Map<String,Object> map = new HashMap<String,Object>();
        map.put("from", from);
        map.put("to", to);
        map.put("timestamp", timestamp);
        map.put("body", body);
        
        JSONObject newNote = new JSONObject(map);
        return newNote.toString();        
	}
}
