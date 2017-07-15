package com.example.sebastiangebert.worktime.model

import com.example.sebastiangebert.worktime.infrastructure.FlexTimeRepository
import com.example.sebastiangebert.worktime.model.interfaces.FlexTimeAccount
import org.joda.time.DateTime

class FlexTimeAccountModel(val repository: FlexTimeRepository) : FlexTimeAccount {
    override fun addEntryNow(): DateTime {
        val entry = DateTime.now()
        this.repository.add(entry)

        return entry
    }

    override fun entriesInInterval(from: DateTime, to: DateTime) =
            this.entriesInInterval(from..to)

    override fun entriesInInterval(interval: ClosedRange<DateTime>) =
            this.repository.filter { it in interval }
}