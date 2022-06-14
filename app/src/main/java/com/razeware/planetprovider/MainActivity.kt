package com.razeware.planetprovider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val helper = DBHelper(this)

    helper.readableDatabase.rawQuery("select * from planets", arrayOf<String>()).use { cursor ->
      while (cursor != null && cursor.moveToNext()) {
        Log.e("MYTEST", cursor.getString(0)
            + ", " + cursor.getString(1)
            + ", " + cursor.getString(2)
            + ", " + cursor.getString(3))
      }
    }
  }
}

