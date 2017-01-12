package joejava.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class HelloAndroid extends Activity{
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
        
                
     // Load a Spinner and bind it to a data query.
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
                        HelloAndroid.this.finish();
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