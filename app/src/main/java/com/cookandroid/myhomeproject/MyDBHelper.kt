package com.cookandroid.myhomeproject

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.fragment.app.FragmentActivity

class MyDBHelper(context: FragmentActivity) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {

    companion object {
        private const val DATABASE_NAME = "todo.db"
        private const val DATABASE_VER = 1
        const val TABLE_NAME = "todo"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val sql: String = "create table $TABLE_NAME(_id integer primary key autoincrement, " +
                "title text, message text, writedate long)"
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("drop table if exists $TABLE_NAME")
    }
}