package joejava.android;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.jar.Attributes.Name;

import org.apache.http.auth.AuthenticationException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.GroupMembership;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Groups;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class GoogleAndroid extends Activity{
	public static final int DIALOG_PAUSED_ID = 0;
	public static final int DIALOG_GAMEOVER_ID = 1;
	
	public static final int JOE = 228;
	
	public static final String LOG = "log";
	
	/** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.hobbit_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
        
        ContentResolver cr = getContentResolver();
        
        /*
         * URIs
         * 
         * General Contact info = ContactsContract.Contacts.CONTENT_URI
         * Data = ContactsContract.Data.CONTENT_URI
         * Group = ContactsContract.Groups.CONTENT_URI
         * 
         */
        
        Cursor group = cr.query(Groups.CONTENT_URI, null, null, null, null);
        while(group.moveToNext()){
        	String id = group.getString(group.getColumnIndex(Groups._ID));
        	String title = group.getString(group.getColumnIndex(Groups.TITLE));
        	
        	Log.i(LOG, id + " " + title);
        }
        
        //READ
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
        					null,
        					ContactsContract.Contacts._ID + " = ?", //null,
        					new String[]{"101"},//null,
        					null);
        
		URL POST_URL = null;
		try {
			POST_URL = new URL("https://www.google.com/m8/feeds/contacts/default/full");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/*
		ContactsService contactService = new ContactsService("Joe");
		try {
			contactService.setUserCredentials("josephpconley@gmail.com", "theone08");
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		
        while (cur.moveToNext()) {
        	ContactEntry contact = new ContactEntry();
        	boolean sync = true;
        	
        	String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
        	
    		Cursor data = cr.query(ContactsContract.Data.CONTENT_URI,
    								null,
    								ContactsContract.Data.CONTACT_ID + " = ?",// AND " + ContactsContract.Data.MIMETYPE + " = ?",
    								new String[]{id},
    								null);

    		while (data.moveToNext()){
	    		String dataId = data.getString(data.getColumnIndex(ContactsContract.Data._ID));
	    		String mimeType = data.getString(data.getColumnIndex(ContactsContract.Data.MIMETYPE));

	    		String data1 = data.getString(data.getColumnIndex(ContactsContract.Data.DATA1));
	    		
	    		//Log.i(LOG, mimeType + " " + data1);
	    		
	    		if(mimeType.equals(StructuredName.CONTENT_ITEM_TYPE)){
	    			String firstName = data.getString(data.getColumnIndex(StructuredName.GIVEN_NAME));
	    			String lastName = data.getString(data.getColumnIndex(StructuredName.FAMILY_NAME));

	    			Name name = new Name();
	    			name.setGivenName(new GivenName(firstName,null));
	    			name.setFamilyName(new FamilyName(lastName,null));
	    			contact.setName(name);
	    		}
	    		
	    		if(mimeType.equals(Phone.CONTENT_ITEM_TYPE)){
	    			String phone = data.getString(data.getColumnIndex(Phone.NUMBER));
	    			
	    			PhoneNumber number = new PhoneNumber();
	    			number.setPhoneNumber(phone);
	    			contact.addPhoneNumber(number);
	    		}

	    		if(mimeType.equals(Email.CONTENT_ITEM_TYPE)){
	    			String email = data.getString(data.getColumnIndex(Email.DATA));
	    			
	    			com.google.gdata.data.extensions.Email gmail = new com.google.gdata.data.extensions.Email();
	    			gmail.setAddress(email);
	    			contact.addEmailAddress(gmail);
	    		}	    		
	    		
	    		if(mimeType.equals(GroupMembership.CONTENT_ITEM_TYPE)){
	    			sync = false;
	    		}
    		}
    		
    		if(sync){
    			Log.i(LOG, contact.toString());
    			
    			try {
					contactService.insert(POST_URL, contact);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    		
    		//Log.i(LOG, cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.GroupMembership.GROUP_ROW_ID)));
    		
    		String[] cols = cur.getColumnNames();
    		for(int i=0;i<cols.length;i++){
    			Log.i(LOG,i + " " + cols[i]);
    		}
    		
    		String hasPhone = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
    		if(Integer.parseInt(hasPhone) > 0){
    			Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
    		 		    				null, 
    		 		    				ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?", 
    		 		    				new String[]{id}, 
    		 		    				null);
    		 	while (pCur.moveToNext()) {
    		 		String number = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
    		 		Log.i(LOG, number);
    		 	} 
    		 	pCur.close();
    		}
    		Cursor emailCur = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, 
    									null,
    									ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?", 
    									new String[]{id}, 
    									null); 
    		while (emailCur.moveToNext()) { 
    			String email = emailCur.getString(emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
    		 	String emailType = emailCur.getString(emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE));
    		 	Log.i(LOG, email);
    		 	Log.i(LOG, emailType);
    		} 
    		emailCur.close();   
    		
    		Cursor bday = cr.query(ContactsContract.Data.CONTENT_URI,
    								new String[] {  ContactsContract.Contacts.DISPLAY_NAME,                         
    	            								ContactsContract.CommonDataKinds.Event.CONTACT_ID,
    	            								ContactsContract.CommonDataKinds.Event.START_DATE},
    								ContactsContract.Data.CONTACT_ID + " = ?",
    								new String[]{id},
    								null);
    		while(bday.moveToNext()){
    			Log.i(LOG,bday.getString(0) + " " + bday.getString(1) + " " + bday.getString(2));
    		}
        }
    	cur.close();
    	*/
    	
        
        //INSERT
        /*
        ContentValues values = new ContentValues();
        values.put(RawContacts.ACCOUNT_NAME, "Frodo Baggins");
        
        Uri uri = cr.insert(Contacts.CONTENT_URI, values);
        Log.i(LOG,uri.toString());
        */
        
     // Load a Spinner and bind it to a data query.
        /*
        String[] PROJECTION = new String[] {
                People._ID, People.NAME
        };

        Spinner contacts = (Spinner)findViewById(R.id.contacts);
        Cursor cur = managedQuery(People.CONTENT_URI, PROJECTION, null, null, null);
        
        SimpleCursorAdapter adapter2 = new SimpleCursorAdapter(this,
        	    android.R.layout.simple_spinner_item, // Use a template
        	                                          // that displays a
        	                                          // text view
        	    cur, // Give the cursor to the list adapter
        	    new String[] {People.NAME}, // Map the NAME column in the
        	                                         // people database to...
        	    new int[] {android.R.id.text1}); // The "text1" view defined in
        	                                     // the XML template
        	                                         
    	adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	contacts.setAdapter(adapter2);
    	*/  
    }
    
    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.new_game:
            showDialog(0);
            return true;
        case R.id.quit:
        	showDialog(1);
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    //Dialog
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
               .setCancelable(false)
               .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                        GoogleAndroid.this.finish();
                   }
               })
               .setNegativeButton("No", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                   }
               });
        AlertDialog alert = builder.create();        
        
        /*
        switch(id) {
        case DIALOG_PAUSED_ID:
            // do the work to define the pause Dialog
            break;
        case DIALOG_GAMEOVER_ID:
            // do the work to define the game over Dialog
            break;
        default:
            dialog = null;
        }
        */
        return alert;
    }
    
    //Listener
    public void clickButton(View view){
    	Context context = getApplicationContext();
    	CharSequence text = "All that is gold does not glitter...";
    	int duration = Toast.LENGTH_SHORT;
    	
    	Toast toast = Toast.makeText(context, text, duration);
    	toast.show();
    }
    
    public class MyOnItemSelectedListener implements OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        	Toast.makeText(parent.getContext(), "The hobbit is " + parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView parent) {
          // Do nothing.
        }
    }    
}