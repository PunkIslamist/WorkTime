package com.example.sebastiangebert.worktime.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.sebastiangebert.worktime.R
import com.example.sebastiangebert.worktime.infrastructure.FlexTimeStorage
import com.example.sebastiangebert.worktime.infrastructure.SqliteDatabase
import com.example.sebastiangebert.worktime.viewmodels.TimePeriod
import com.example.sebastiangebert.worktime.views.LogEntries.LogEntryAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.joda.time.DateTime

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val storage = FlexTimeStorage(SqliteDatabase(this))
        recycler_logEntries.adapter = LogEntryAdapter(TimePeriod(storage))
    }

    override fun onResume() {
        super.onResume()
    }

    @Suppress("UNUSED_PARAMETER")
    fun logHandler(eventSource: View?) {
        FlexTimeStorage(SqliteDatabase(this)).add(DateTime.now())
    }
}