package com.example.sebastiangebert.worktime

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context) : SQLiteOpenHelper(context, "WorkTime.db", null, 4) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "" +
                "CREATE TABLE TimeLogEntry (" +
                "Id integer primary key autoincrement, " +
                "Timestamp int not null" +
                ")"

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTable = "DROP TABLE TimeLogEntry"

        db?.execSQL(dropTable)
        this.onCreate(db)
    }
}