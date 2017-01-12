package joejava.sms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class SplashActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    setContentView(R.layout.splash);
	}

	@Override
	protected void onResume() {
		final int SPLASH_SCREEN_TIME_IN_MILLIS = 500;
	    super.onResume();
	    Thread thread = new Thread() {
	        @Override
	        public void run() {
	            try {
	                Thread.sleep(SPLASH_SCREEN_TIME_IN_MILLIS);
	            } catch (InterruptedException e) {
	            } finally{
	            	finish();
                    goToNextScreen();
	            }
	        }
	    };

	    thread.start();
	}

	protected void goToNextScreen() {
	    Intent intent = new Intent(this, MainActivity.class);
	    startActivity(intent);
	}
}
