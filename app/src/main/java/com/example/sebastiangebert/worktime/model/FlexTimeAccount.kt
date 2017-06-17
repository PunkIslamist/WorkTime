package com.example.sebastiangebert.worktime.model

import com.example.sebastiangebert.worktime.infrastructure.FlexTimeRepository
import org.joda.time.DateTime

class FlexTimeAccount(val repository: FlexTimeRepository) {
    fun addEntryNow(): DateTime {
        val entry = DateTime.now()
        this.repository.write(entry)

        return entry
    }

    fun entriesInInterval(from: DateTime = DateTime(0), to: DateTime = DateTime.now()) =
            this.entriesInInterval(from..to)

    fun entriesInInterval(interval: ClosedRange<DateTime>) =
            this.repository
                    .readAll()
                    .filter { it in interval }
}