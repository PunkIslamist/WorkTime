package com.example.sebastiangebert.worktime.model

import com.example.sebastiangebert.worktime.infrastructure.FlexTimeRepository
import org.joda.time.DateTime
import org.joda.time.Interval

class FlexTimeAccount(val repository: FlexTimeRepository) {
    // TODO remove
    val Entries: List<DateTime>
        get() = this.repository.readAll()

    fun add(entry: DateTime) {
        this.repository.write(entry)
    }

    fun entriesInInterval(from: DateTime = DateTime(0), upTo: DateTime = DateTime.now()) =
            this.entriesInInterval(Interval(from, upTo))

    fun entriesInInterval(interval: Interval) =
            this.repository
                    .readAll()
                    .filter { interval.contains(it) }
}