package com.example.sebastiangebert.worktime.infrastructure

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteDatabase(context: Context) : SQLiteOpenHelper(context, "WorkTime.db", null, 4), Database {
    private val database = this.writableDatabase

    override fun select(): Iterable<String> {
        val entries = mutableListOf<String>()
        val allTimeLogEntries = this.database.query(
                "TimeLogEntry", null, null, null, null, null, "Timestamp ASC", null)

        allTimeLogEntries.use {
            val columnIndex = allTimeLogEntries.getColumnIndex("Timestamp")

            while (allTimeLogEntries.moveToNext()) {
                entries.add(it.getString(columnIndex))
            }
        }

        return entries
    }

    override fun insert(values: Iterable<String>) {
        values.forEach {
            val content = ContentValues(1)
            content.put("Timestamp", it)
            this.database.insertOrThrow("TimeLogEntry", null, content)
        }
    }

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