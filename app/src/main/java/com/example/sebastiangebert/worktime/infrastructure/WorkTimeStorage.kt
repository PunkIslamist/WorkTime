package com.example.sebastiangebert.worktime.infrastructure

import android.content.Context
import android.database.Cursor
import org.joda.time.DateTime
import java.io.Closeable

class WorkTimeStorage(context: Context) : Closeable, WorkTimeRepository {
    private val repository = Database(context).writableDatabase

    override fun write(timestamp: DateTime) {
        this.repository.execSQL("INSERT INTO TimeLogEntry (Timestamp) VALUES (strftime('%s','$timestamp'))")
    }

    override fun readAll(): List<DateTime> {
        val entries = MutableList(0, { DateTime() })
        val allTimeLogEntries = this.repository.query(
                "TimeLogEntry", null, null, null, null, null, "Timestamp ASC", null)

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
