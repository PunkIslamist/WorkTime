package com.example.sebastiangebert.worktime.model

import com.example.sebastiangebert.worktime.infrastructure.FlexTimeRepository
import org.joda.time.DateTime
import org.joda.time.Interval

class FlexTimeAccount(val repository: FlexTimeRepository) {
    val Entries: List<DateTime>
        get() = this.repository.readAll()

    fun add(entry: DateTime) {
        this.repository.write(entry)
    }

    fun entriesInInterval(from: DateTime = DateTime(0), to: DateTime = DateTime.now()) =
            this.entriesInInterval(Interval(from, to))

    fun entriesInInterval(interval: Interval) =
            this.repository
                    .readAll()
                    .filter { interval.contains(it) }
}