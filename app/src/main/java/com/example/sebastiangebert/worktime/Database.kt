package com.example.sebastiangebert.worktime

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context) : SQLiteOpenHelper(context, "WorkTime.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "" +
                "CREATE TABLE TimeLogEntry (" +
                "Id integer primary key autoincrement, " +
                "Date date default CURRENT_DATE, " +
                "Time time default CURRENT_TIME" +
                ")"

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //http://stackoverflow.com/a/3424444
    }
}