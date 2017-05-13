package com.example.sebastiangebert.worktime

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.joda.time.LocalDate
import org.joda.time.LocalTime

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        val db = Database(this).writableDatabase
        val entries = MutableList(0, { Pair(LocalDate(), LocalTime()) })

        db.use {
            db.execSQL("INSERT INTO TimeLogEntry DEFAULT VALUES")
            val allTimeLogEntries = db.query("TimeLogEntry", null, null, null, null, null, null, null)

            allTimeLogEntries.use {
                while (allTimeLogEntries.moveToNext()) {
                    val date = LocalDate(allTimeLogEntries.getString(allTimeLogEntries.getColumnIndex("Date")))
                    val time = LocalTime(allTimeLogEntries.getString(allTimeLogEntries.getColumnIndex("Time")))
                    entries.add(Pair(date, time))
                }
            }
        }

        text.text = entries
                .map { dateTime -> "${dateTime.first}\t${dateTime.second}" }
                .fold("", { acc, curr -> "$acc\n$curr" })
    }
}