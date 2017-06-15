package com.example.sebastiangebert.worktime.infrastructure

interface FlexTimeRepository {
    fun write(timestamp: org.joda.time.DateTime)
    fun readAll(): List<org.joda.time.DateTime>
}