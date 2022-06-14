/*
 * Copyright (c) 2020 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.razeware.planetprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.database.sqlite.SQLiteDatabase
import android.content.UriMatcher
import androidx.core.content.ContentProviderCompat


class PlanetContentProvider : ContentProvider() {

  companion object {
    const val NAME_CODE = 10
    const val REBEL_BASE_CODE = 20

    private const val AUTHORITY = "com.razeware.planetprovider"
    private const val NAME_PATH = "name"
    private const val REBEL_BASE_PATH = "rebel_base"

    val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    init {
      uriMatcher.addURI(AUTHORITY, NAME_PATH, NAME_CODE)
      uriMatcher.addURI(AUTHORITY, REBEL_BASE_PATH, REBEL_BASE_CODE)
    }
  }

  private lateinit var dbHelper: DBHelper
  private lateinit var db: SQLiteDatabase

  override fun insert(p0: Uri, p1: ContentValues?): Uri? {
    TODO("not implemented")
  }


  override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor {
    this.db = dbHelper.readableDatabase

    return when (uriMatcher.match(uri)) {
      NAME_CODE -> this.db.rawQuery("select id as _id, name from planets", arrayOf<String>())
      REBEL_BASE_CODE -> this.db.rawQuery("select id as _id, name, rebel_base_lat, rebel_base_lng from planets", arrayOf<String>())
      else -> throw IllegalArgumentException("Unknown URI: $uri")
    }
  }







  override fun onCreate(): Boolean {
    this.dbHelper = DBHelper(ContentProviderCompat.requireContext(this))
    return true
  }

  override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
    TODO("not implemented")
  }

  override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
    TODO("not implemented")
  }

  override fun getType(p0: Uri): String {
    TODO("not implemented")
  }
}