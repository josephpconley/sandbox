package joejava.sms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import joejava.model.Note;
import joejava.util.RestTool;

import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity implements DialogInterface.OnMultiChoiceClickListener {
	
	public Note[] notes = null;
	private static final int FILTER_DIALOG = 0;
	private String[] items = null;
	private boolean[] selected = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        refresh();
        
    }
    
    public void refresh(){
    	try {
        	notes = getNotes();
			setListAdapter(new ArrayAdapter<Note>(this, R.layout.list, notes));
		} catch (JSONException e) {
			e.printStackTrace();
		}
        
        ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				ListView lv = (ListView)parent;
				String tags = null;
				try {
					Note note = (Note) lv.getAdapter().getItem(position);
					Log.i("log", note.getJSONObject().toString());
					
					JSONArray j = note.getJSONObject().getJSONArray("tags");
					List<String> str = new ArrayList<String>();
					for(int i=0; i<j.length(); i++){
						str.add(j.getString(i));
					}
					tags = str.toString();
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
				Toast.makeText(getApplicationContext(), tags, Toast.LENGTH_SHORT).show();				
			}
        });
        
        registerForContextMenu(getListView());
    	
    }
    
    //Context options
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    	super.onCreateContextMenu(menu, v, menuInfo);
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.context_menu, menu);
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
    	switch (item.getItemId()) {
    	case R.id.edit:
    		Toast.makeText(getApplicationContext(), "Edit", Toast.LENGTH_SHORT).show();
    		return true;
    	case R.id.delete:
    		try {
    			JSONObject j = notes[(int)info.id].getJSONObject().getJSONObject("_id");
    			ObjectId id = new ObjectId(j.getInt("_time"), j.getInt("_machine"), j.getInt("_inc"));
    			Log.i("log", id.toString());
    			RestTool.post(RestTool.NOTES_URL + "delete/" + id, "");
				Toast.makeText(getApplicationContext(), "Note " + id + " has been deleted", Toast.LENGTH_SHORT).show();
			} catch (JSONException e) {
				e.printStackTrace();
			}
    		refresh();
    		return true;
    	default:
    		return super.onContextItemSelected(item);
    	}
    }    
    
    
    //Menu options
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.new_note:
        	Intent intent = new Intent(this, NewNoteActivity.class);
        	startActivity(intent);
        	return true;
        case R.id.filter:
        	showDialog(FILTER_DIALOG);
        	return true;
        case R.id.sync:
        	Intent i = new Intent(this, SMSActivity.class);
        	startActivity(i);
        	return true;
        case R.id.quit:
        	finish();
        	return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }    
    
    public void onResume(){
    	super.onResume();
    	refresh();
    }

    @Override
	protected Dialog onCreateDialog(int id) {
    	String result = RestTool.get(RestTool.TAGS_URL + "listJSON");
    	
    	try {
			JSONArray jsonArray = new JSONArray(result);
			items = new String[jsonArray.length()];
			for(int i=0; i<items.length; i++){
				JSONObject obj = jsonArray.getJSONObject(i);
				items[i] = obj.getString("label");
			}
			
			selected = new boolean[items.length];
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle("Pick a tag");
    	builder.setMultiChoiceItems(items, selected, this);
    	
    	AlertDialog alert = builder.create();
    	alert.show();
    	return super.onCreateDialog(id);
	}

    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
		selected[which] = true;
    	Log.i("log", Arrays.toString(selected));
		refresh();
    	dialog.dismiss();
	}
    
    private String getTag(){
    	if(selected != null){
    		for(int i=0;i<selected.length;i++){
        		if(selected[i] == true){
        			return items[i];
        		}
        	}    		
    	}
    	
    	return null;
    }
    
	private Note[] getNotes() throws JSONException{
    	Note[] notes = null;
    	
    	try {
    		String q = getTag();
    		String url = RestTool.NOTES_URL + "listJSON";
    		Log.i("joe", url);
    		if(q != null){
    			Log.i("joe", q);
    			url += "?q=" + q;
    		}
    		
    		String result = RestTool.get(url);
    		if(result != null){
    			Log.i("joe", result);	
    			
        		JSONArray json = new JSONArray(result);
        		
        		notes = new Note[json.length()];
            	for(int i=0; i<json.length(); i++){
            		notes[i] = new Note(json.getJSONObject(i)); 
            	}
    		}

        } catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

        return notes;
    }
}