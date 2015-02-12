package joejava.sms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import joejava.util.RestTool;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewNoteActivity extends Activity implements OnClickListener {
    
	private EditText tags;
	private EditText text;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        
        tags = (EditText) this.findViewById(R.id.tags);
        text = (EditText) this.findViewById(R.id.text);
        
        Button b = (Button)findViewById(R.id.button1);
        b.setOnClickListener(this);
	}

	public void onClick(View v) {
        Map<String,Object> map = new HashMap<String,Object>();
        String[] t = tags.getText().toString().split(",");
        map.put("tags", new JSONArray(Arrays.asList(t)));
        map.put("text", text.getText().toString());
        
        JSONObject newNote = new JSONObject(map);
        Log.i("log", newNote.toString());
        RestTool.post(RestTool.NOTES_URL + "saveJSON", newNote.toString());		
		Toast.makeText(getApplicationContext(), "New note has been saved", Toast.LENGTH_SHORT).show();
		finish();
	}
}
