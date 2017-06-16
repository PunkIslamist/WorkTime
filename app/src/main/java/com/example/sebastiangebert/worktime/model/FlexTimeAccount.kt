package com.example.sebastiangebert.worktime.model

import com.example.sebastiangebert.worktime.infrastructure.FlexTimeRepository
import org.joda.time.DateTime

class FlexTimeAccount(val repository: FlexTimeRepository) {
    val Entries: List<DateTime>
        get() = this.repository.readAll()

    fun add(entry: DateTime) {
        this.repository.write(entry)
    }

    fun entriesInPeriod(from: DateTime = DateTime(0), to: DateTime = DateTime.now())
            : List<DateTime> {
        TODO("not implemented")
    }
}