package joejava.sms.receiver;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.gsm.SmsMessage;
import android.util.Log;
/*
 * config
 * 
 * <!-- SMS Receiver -->
		<!-- 
        <receiver android:name=".receiver.SMSReceiver" android:enabled="true">
	      <intent-filter>
	        <action android:name="android.provider.Telephony.SMS_RECEIVED" />
	      </intent-filter>
    	</receiver>
		 -->
 */
public class SMSReceiver extends BroadcastReceiver{

	public static final int THREAD_ID = 1;
	public static final int OTHER_NUMBER = 2;
	public static final int FROM = 3;
	public static final int DATE = 4;
	public static final int READ = 6;
	public static final int BODY = 13;
	
	public static final String ME = "6104163219";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("joefeed", "Got SMS Message");
		
		Bundle bundle = intent.getExtras();
		Object messages[] = (Object[]) bundle.get("pdus");
		SmsMessage smsMessage[] = new SmsMessage[messages.length];

		for (int n = 0; n<messages.length; n++) {
			smsMessage[n] = SmsMessage.createFromPdu((byte[]) messages[n]);
		}
		
		SmsMessage sms = smsMessage[0];
		if(sms.getEmailFrom() != null && sms.getEmailFrom().equals("josephpconley@gmail.com")){
			String[] str = sms.getEmailBody().split("\\s");
	   		String to = str[0].replace("(", "").replace(")","");
			String body = sms.getEmailBody().replace(str[0] + " ", "");
	   		
	   		//send off sms
	   		SmsManager.getDefault().sendTextMessage(to, null, body, null, null);

	   		//save to sent folder so it's visible in thread
	   		ContentValues values = new ContentValues();
	   		values.put("address", to);
	   		values.put("body", body);
	   		context.getContentResolver().insert(Uri.parse("content://sms/sent"), values);
	   		
	   		abortBroadcast();
		}else{
			//
			syncSMS(sms);	
		}
		
		//ignore the text from my website (might have to delete instead, I think this broadcast occurs after the native SMS app)
		//set thread to "read"
		
		//update my application
		
	}
	
	private void syncSMS(SmsMessage msg){
		/*
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("from", msg.getOriginatingAddress());
        map.put("to", ME);
        map.put("timestamp", msg.getTimestampMillis());
        map.put("body", msg.getMessageBody());
        map.put("read", "0");
        	
        JSONObject newNote = new JSONObject(map);
        ArrayList<String> list = new ArrayList<String>();
        list.add(newNote.toString());
        RestTool.post(RestTool.SMS_URL + "save", list.toString());
        */
	}
}
