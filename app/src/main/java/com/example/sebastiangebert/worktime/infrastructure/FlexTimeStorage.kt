package com.example.sebastiangebert.worktime.infrastructure

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import org.joda.time.DateTime
import java.io.Closeable

class FlexTimeStorage(context: Context) : Closeable, FlexTimeRepository {
    private val repository = Database(context).writableDatabase
    private val cache = this.selectAll()

    override fun write(timestamp: DateTime) {
        val values = ContentValues(1)
        values.put("Timestamp", timestamp.toUnixTime())

        this.repository.insertOrThrow("TimeLogEntry", null, values)
    }

    override fun readAll() = this.cache

    override fun close() {
        this.repository.close()
    }

    private fun selectAll(): List<DateTime> {
        val entries = MutableList(0, { DateTime() })
        val allTimeLogEntries = this.repository.query(
                "TimeLogEntry", null, null, null, null, null, "Timestamp ASC", null)

        allTimeLogEntries.use {
            val columnIndex = allTimeLogEntries.getColumnIndex("Timestamp")

            while (allTimeLogEntries.moveToNext()) {
                entries.add(this.asDateTime(it, columnIndex))
            }
        }

        return entries.toList()
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

    private fun DateTime.toUnixTime() = this.millis / 1000
}
