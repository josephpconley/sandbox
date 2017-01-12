package joejava.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Note{

	private JSONObject jsonObj;
	
	public Note(JSONObject j){
		this.jsonObj = j;
	}
	
	public JSONObject getJSONObject(){
		return jsonObj;
	}
	
	public String toString() {
		try {
			return jsonObj.getString("text");
		} catch (JSONException e) {
			return null;
		}
	}

}
