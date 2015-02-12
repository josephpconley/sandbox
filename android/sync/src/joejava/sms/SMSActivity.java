package joejava.sms;

import java.util.ArrayList;
import java.util.List;

import joejava.model.Note;
import joejava.sms.receiver.SMSReceiver;
import joejava.util.RestTool;
import joejava.util.SMSTool;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class SMSActivity extends ListActivity {
	
	public Note[] notes = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);

        //getAllFields();
        getSMSMessages();
        finish();
    }
    
    public void getSMSMessages(){
        Uri allTexts = Uri.parse("content://sms/");
        ContentResolver cr = getContentResolver();
        Cursor c = cr.query(allTexts, 
        					null, 
        					null, //"thread_id = ?",//"read = ?",	
        					null, //new String[]{"8"}, 	//me n ace	(8)	read(1)
        					"date desc");
        Log.i("joesms", Integer.toString(c.getCount()));

        List<String> sms = new ArrayList<String>();
        while(c.moveToNext()){
        	int type = c.getInt(c.getColumnIndex("type"));

        	String from = c.getString(c.getColumnIndex("address"));
        	String to = SMSReceiver.ME;
        	if(type == 2){
        		from = SMSReceiver.ME;
        		to = c.getString(c.getColumnIndex("address"));
        	}
        	
        	//date column returns the local time of the sender, use sort_index instead
            sms.add(SMSTool.toMap(from, to, c.getLong(c.getColumnIndex("sort_index")), c.getString(c.getColumnIndex("body"))));
        }
        RestTool.post(RestTool.SMS_URL + "save", sms.toString());
    }
    
    public void getAllFields(){
        Uri allTexts = Uri.parse("content://mms");
        ContentResolver cr = getContentResolver();
        Cursor c = cr.query(allTexts, 
        					null, 
        					null, //"thread_id = ?",//"read = ?",	
        					null, //new String[]{"8"}, 	//me n ace	(8)	read(1)
        					"date desc");
        Log.i("joesms", Integer.toString(c.getCount()));

        List<String> sms = new ArrayList<String>();
        while(c.moveToNext()){
        	/*
        	int type = c.getInt(c.getColumnIndex("type"));

        	String from = c.getString(c.getColumnIndex("address"));
        	String to = SMSReceiver.ME;
        	if(type == 2){
        		from = SMSReceiver.ME;
        		to = c.getString(c.getColumnIndex("address"));
        	}

        	String body = c.getString(c.getColumnIndex("body"));
        	*/
        	
        	for(int i=0;i<c.getColumnCount();i++){
    			Log.i("joe", c.getColumnName(i) + " " + c.getString(i));
    		}        	
        }
    }    
}