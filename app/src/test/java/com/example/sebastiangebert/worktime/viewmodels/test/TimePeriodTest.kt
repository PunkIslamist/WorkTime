package com.example.sebastiangebert.worktime.viewmodels.test

import com.example.sebastiangebert.worktime.model.WorkTimeRepository
import com.example.sebastiangebert.worktime.viewmodels.TimePeriod
import org.joda.time.DateTime
import org.junit.Assert
import org.junit.Test

class RepoMock(var entries: MutableList<DateTime>) : WorkTimeRepository {
    override fun write(timestamp: DateTime) {
        this.entries.add(timestamp)
    }

    override fun readAll() = this.entries
}

class TimePeriodTest {
    @Test
    fun NoEndPointsSet_ReturnAllDateTimes() {
        val expected = arrayOf(
                DateTime("2017-01-02T08:00:00"),
                DateTime("2017-01-02T17:00:00"),
                DateTime("2017-01-03T09:01:15"),
                DateTime("2017-01-03T17:35:26")
        )
        val testInstance = TimePeriod(RepoMock(expected.toMutableList()))

        val actual = testInstance.All.toTypedArray()

        Assert.assertArrayEquals(expected, actual)
    }

    @Test
    fun MoveStartAhead_DontReturnEarlierEntries() {
        val dates = arrayOf(
                DateTime("2017-01-02T08:00:00"),
                DateTime("2017-01-02T17:00:00"),
                DateTime("2017-01-03T09:01:15"),
                DateTime("2017-01-03T17:35:26")
        )
        val expected = dates.copyOfRange(2, 4)
        val testInstance = TimePeriod(RepoMock(dates.toMutableList()))

        testInstance.Start = DateTime("2017-01-03")
        val actual = testInstance.All.toTypedArray()

        Assert.assertArrayEquals(expected, actual)
    }
}