package com.josephpconley

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

/**
 * User: jconley
 * Date: 12/5/13
 */
class DisplayMessageActivity extends Activity {
  override def onCreate(savedInstanceState : Bundle) {
    super.onCreate(savedInstanceState)
    getActionBar().setDisplayHomeAsUpEnabled(true)

    // Get the message from the intent
    val intent = getIntent()
    val message = intent.getStringExtra("test")

    // Create the text view
    val textView = new TextView(this)
    textView.setTextSize(40)
    textView.setText(message)

    // Set the text view as the activity layout
    setContentView(textView)
  }
}
