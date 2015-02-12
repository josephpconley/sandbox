package com.josephpconley

import android.app.{ListActivity, Activity}
import android.os.{AsyncTask, StrictMode, Bundle}
import android.widget._
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.{StatusLine, HttpResponse}
import org.apache.http.client.methods.HttpGet
import java.io.{BufferedReader, InputStreamReader}
import android.util.Log
import org.json.JSONArray


/**
 * User: jconley
 * Date: 12/5/13
 */
class List extends Activity{
  override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.table)

    val layout = findViewById(R.id.table).asInstanceOf[TableLayout]
    layout.setVerticalScrollBarEnabled(true)
    layout.setHorizontalScrollBarEnabled(true)

    Log.i("joe", layout.toString)
    val jsonStr = new HttpGetTask().execute("http://datahub-jconley.rhcloud.com/ncaa.json").get()
    Log.i("json", jsonStr)
    val arr = new JSONArray(jsonStr)
    val fields = Seq("id", "away", "home", "kev", "tim")

    val header = new TableRow(this)
    fields.foreach { k =>
        val tv = new TextView(this)
        tv.setText(k)
        header.addView(tv)
    }
    layout.addView(header, 0)

    (0 to arr.length() - 1).foreach{ r =>
      val row = new TableRow(this)
      val obj =  arr.getJSONObject(r)

      fields.foreach{ k =>
        val tv = new TextView(this)
        val str = if(k.size == 4) obj.getDouble(k).toInt.toString else obj.getString(k)
        tv.setText(str)
        row.addView(tv)
      }

      layout.addView(row, r + 1)
    }
  }

}
