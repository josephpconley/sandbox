package com.josephpconley

import android.app.Activity
import android.os.Bundle
import android.view.{MenuItem, Menu, View}
import android.content.Intent
import android.widget.{Toast, EditText}
import android.util.Log

/**
 * User: jconley
 * Date: 12/5/13
 */
class HomeActivity extends Activity {
  override def onCreate(savedInstanceState : Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main)
  }

  def sendMessage(view: View) {
    val intent = new Intent(this, classOf[DisplayMessageActivity])
    val edit = findViewById(R.id.edit_message).asInstanceOf[EditText]
    Toast.makeText(this, edit.getText.toString, Toast.LENGTH_SHORT).show()

    intent.putExtra("test", edit.getText.toString)
    startActivity(intent)
  }

  override def onCreateOptionsMenu(menu: Menu): Boolean = {
    val inflater = getMenuInflater
    inflater.inflate(R.menu.action_bar, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override def onOptionsItemSelected(item: MenuItem): Boolean = {
    item.getItemId match {
      case R.id.action_search => openSearch;  true
      case R.id.action_settings => openSettings;  true
      case _ => super.onOptionsItemSelected(item)
    }
  }

  def openSearch = println("openSearch")
  def openSettings = println("openSettings")
}
