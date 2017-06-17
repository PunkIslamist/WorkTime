package com.example.sebastiangebert.worktime.model

import com.example.sebastiangebert.worktime.infrastructure.FlexTimeRepository
import org.joda.time.DateTime

class FlexTimeAccount(val repository: FlexTimeRepository) {
    // TODO remove
    val Entries: List<DateTime>
        get() = this.repository.readAll()

    fun add(entry: DateTime) {
        this.repository.write(entry)
    }

    fun entriesInInterval(from: DateTime = DateTime(0), to: DateTime = DateTime.now()) =
            this.entriesInInterval(from..to)

    fun entriesInInterval(interval: ClosedRange<DateTime>) =
            this.repository
                    .readAll()
                    .filter { it in interval }
}