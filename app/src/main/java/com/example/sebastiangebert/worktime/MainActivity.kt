package com.example.sebastiangebert.worktime

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.joda.time.DateTime

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        val storage = WorkTimeStorage(this)
        var entries: List<DateTime> = List(0, { DateTime() })

        storage.use {
            storage.log()
            entries = storage.getAll()
        }

        text.text = entries
                .fold("", { acc, curr -> "$acc\n$curr" })
    }
}