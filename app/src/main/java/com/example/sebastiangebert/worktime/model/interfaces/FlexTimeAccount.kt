package com.example.sebastiangebert.worktime.model.interfaces

import org.joda.time.DateTime

interface FlexTimeAccount {
    fun addEntryNow(): DateTime

    fun entriesInInterval(from: DateTime = DateTime(0), to: DateTime = DateTime.now()): List<DateTime>

    fun entriesInInterval(interval: ClosedRange<DateTime>): List<DateTime>
}