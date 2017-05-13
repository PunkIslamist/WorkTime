package com.example.sebastiangebert.worktime

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        val db = Database(this).writableDatabase

        db.use {
            db.execSQL("INSERT INTO TimeLogEntry DEFAULT VALUES")
            val allTimeLogEntries = db.query("TimeLogEntry", null, null, null, null, null, null, null)
            var resultTable = ""

            allTimeLogEntries.use {
                while (allTimeLogEntries.moveToNext()) {
                    val date = allTimeLogEntries.getString(allTimeLogEntries.getColumnIndex("Date"))
                    val time = allTimeLogEntries.getString(allTimeLogEntries.getColumnIndex("Time"))
                    resultTable += "$date\t$time\n"
                }
            }

            text.text = resultTable
        }
    }
}