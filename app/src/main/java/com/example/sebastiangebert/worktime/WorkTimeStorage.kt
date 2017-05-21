package com.example.sebastiangebert.worktime

import android.content.Context
import android.database.Cursor
import org.joda.time.DateTime
import java.io.Closeable

class WorkTimeStorage(context: Context) : Closeable {
    private val repository = Database(context).writableDatabase

    /**
     * Log the current time to the storage.
     */
    fun log() {
        this.repository.execSQL("INSERT INTO TimeLogEntry (Timestamp) VALUES (strftime('%s','now'))")
    }

    /**
     * Get all log entries in the storage.
     */
    fun getAll(): List<DateTime> {
        val entries = MutableList(0, { DateTime() })
        val allTimeLogEntries = this.repository.query("TimeLogEntry", null, null, null, null, null, null, null)

        allTimeLogEntries.use {
            val columnIndex = allTimeLogEntries.getColumnIndex("Timestamp")

            while (allTimeLogEntries.moveToNext()) {
                entries.add(this.asDateTime(it, columnIndex))
            }
        }


        return entries
    }

    override fun close() {
        this.repository.close()
    }

    private fun asDateTime(cursor: Cursor, columnIndex: Int): DateTime {
        val timestamp = this.asUnixTimestamp(cursor, columnIndex)

        return DateTime(timestamp)
    }

    /**
     * Returns the value of the specified column of the current row as a unix timestamp value.
     * SQLite saves the unix timestamp without milliseconds,
     * but Joda-Time expects a millisecond resolution.
     */
    private fun asUnixTimestamp(cursor: Cursor, columnIndex: Int): Long {
        return cursor.getInt(columnIndex).toLong() * 1000
    }
}
