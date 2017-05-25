package com.example.sebastiangebert.worktime.model

import org.joda.time.DateTime

interface WorkTimeRepository {
    fun write(timestamp: DateTime)
    fun readAll(): List<DateTime>
}