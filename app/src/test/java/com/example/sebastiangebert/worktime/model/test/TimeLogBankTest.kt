package com.example.sebastiangebert.worktime.model.test

import com.example.sebastiangebert.worktime.infrastructure.WorkTimeRepository
import com.example.sebastiangebert.worktime.model.TimeLogBank
import org.joda.time.DateTime
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TimeLogBankTest {
    companion object TestRepo : WorkTimeRepository {
        var Entries: MutableList<DateTime> = MutableList(0, { DateTime() })

        override fun write(timestamp: DateTime) {
            this.Entries.add(timestamp)
        }

        override fun readAll() = this.Entries.toList()
    }

    val bank = TimeLogBank(TestRepo)

    @Before
    fun Setup() {
        TestRepo.Entries = MutableList(0, { DateTime() })
    }

    @Test
    fun NoEntries_Get_ReturnEmptyList() {
        val expected = List(0, { DateTime() })

        val actual = bank.Entries

        assertEquals(expected, actual)
    }

    @Test
    fun SingleEntry_Get_ReturnSingleEntry() {
        val expected = List(1, { DateTime() })
        TestRepo.Entries = expected.toMutableList()

        val actual = bank.Entries

        assertEquals(expected, actual)
    }

    @Test
    fun MultipleEntries_Get_ReturnAllEntries() {
        val expected = List(1000, { DateTime() })
        TestRepo.Entries = expected.toMutableList()

        val actual = bank.Entries

        assertEquals(expected, actual)
    }

    @Test
    fun NoEntries_AddOne_ReturnOneEntry() {
        val date = DateTime("1990-01-01")
        val expected = listOf(date)

        bank.Add(date)
        val actual = bank.Entries

        assertEquals(expected, actual)
    }
}