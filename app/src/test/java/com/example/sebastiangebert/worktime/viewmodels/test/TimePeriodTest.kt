package com.example.sebastiangebert.worktime.viewmodels.test

import com.example.sebastiangebert.worktime.model.WorkTimeRepository
import com.example.sebastiangebert.worktime.viewmodels.TimePeriod
import org.joda.time.DateTime
import org.junit.Before
import org.junit.Test

class RepoMock : WorkTimeRepository {
    var entries = mutableListOf(
            DateTime("2017-01-02 08:00:00"),
            DateTime("2017-01-02 12:00:00"),

            DateTime("2017-01-02 13:00:00"),
            DateTime("2017-01-02 17:00:00"),

            DateTime("2017-01-03 09:01:15"),
            DateTime("2017-01-03 12:17:26"),

            DateTime("2017-01-03 12:55:15"),
            DateTime("2017-01-03 17:35:26"),

            DateTime("2017-01-04 08:01:15"),
            DateTime("2017-01-04 12:30:15"),

            DateTime("2017-01-04 13:15:26"),
            DateTime("2017-01-04 16:35:26")
    )

    override fun write(timestamp: DateTime) {
        this.entries.add(timestamp)
    }

    override fun readAll() = this.entries


}

class TimePeriodTest {
    var instance = TimePeriod(RepoMock())

    @Before
    fun init() {
        this.instance = TimePeriod(RepoMock())
    }

    @Test
    fun getAllTimestamps() {
    }
}