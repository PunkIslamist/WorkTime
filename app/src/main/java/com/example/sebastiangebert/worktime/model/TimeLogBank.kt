package com.example.sebastiangebert.worktime.model

import com.example.sebastiangebert.worktime.infrastructure.WorkTimeRepository
import org.joda.time.DateTime

class TimeLogBank(val repository: WorkTimeRepository) {
    val Entries: List<DateTime>
        get() = this.repository.readAll()

    fun Add(entry: DateTime) {
        this.repository.write(entry)
    }
}