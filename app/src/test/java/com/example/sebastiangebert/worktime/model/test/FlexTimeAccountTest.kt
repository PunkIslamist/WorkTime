package com.example.sebastiangebert.worktime.model.test

import com.example.sebastiangebert.worktime.infrastructure.FlexTimeRepository
import com.example.sebastiangebert.worktime.model.FlexTimeAccount
import org.joda.time.DateTime
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FlexTimeAccountTest {
    companion object TestRepo : FlexTimeRepository {
        var Entries: MutableList<DateTime> = MutableList(0, { DateTime() })

        override fun write(timestamp: DateTime) {
            this.Entries.add(timestamp)
        }

        override fun readAll() = this.Entries.toList()
    }

    val account = FlexTimeAccount(TestRepo)
    val start = DateTime("1990-01-01")

    @Before
    fun Setup() {
        TestRepo.Entries = MutableList(0, { DateTime() })
    }

    //region Get
    @Test
    fun NoEntries_Get_ReturnEmptyList() {
        val expected = List(0, { DateTime() })

        val actual = account.Entries

        assertEquals(expected, actual)
    }

    @Test
    fun OneEntry_Get_ReturnOneEntry() {
        val expected = List(1, { DateTime() })
        TestRepo.Entries = expected.toMutableList()

        val actual = account.Entries

        assertEquals(expected, actual)
    }

    @Test
    fun MultipleEntries_Get_ReturnAllEntries() {
        val expected = this.testValues(1000)
        TestRepo.Entries = expected.toMutableList()

        val actual = account.Entries

        assertEquals(expected, actual)
    }
    //endregion

    //region GetWithoutParams
    @Test
    fun NoEntries_GetWithoutParameters_ReturnEmptyList() {
        val expected = List(0, { DateTime() })

        val actual = account.entriesInInterval()

        assertEquals(expected, actual)
    }

    @Test
    fun OneEntry_GetWithoutParameters_ReturnOneEntry() {
        val expected = testValues(1)
        TestRepo.Entries = expected.toMutableList()

        val actual = account.entriesInInterval()

        assertEquals(expected, actual)
    }

    @Test
    fun MultipleEntries_GetWithoutParameters_ReturnAllEntries() {
        val expected = testValues(1000)
        TestRepo.Entries = expected.toMutableList()

        val actual = account.entriesInInterval()

        assertEquals(expected, actual)
    }

    @Test
    fun NoEntries_GetWithFromParameter_ReturnEmptyList() {
        val expected = List(0, { DateTime() })

        val actual = account.entriesInInterval(from = this.start)

        assertEquals(expected, actual)
    }
    //endregion

    //region GetWithFromParam
    @Test
    fun OneEntry_GetFromEarliestPossibleDate_ReturnOneEntry() {
        val expected = testValues(1)
        TestRepo.Entries = expected.toMutableList()

        val actual = account.entriesInInterval(from = DateTime(0))

        assertEquals(expected, actual)
    }

    @Test
    fun OneEntry_GetFromDateAfterEntry_ReturnEmptyList() {
        val expected = testValues(0)
        TestRepo.Entries = testValues(1).toMutableList()

        val actual = account.entriesInInterval(from = this.start.plusDays(1))

        assertEquals(expected, actual)
    }

    @Test
    fun MultipleEntries_GetFromEarliestPossibleDate_ReturnAllEntries() {
        val expected = testValues(1000)
        TestRepo.Entries = expected.toMutableList()

        val actual = account.entriesInInterval(from = DateTime(0))

        assertEquals(expected, actual)
    }

    @Test
    fun MultipleEntries_GetFromDateWithinInterval_ReturnEntriesAfterFromDate() {
        val expected = testValues(1000).takeLast(500)
        TestRepo.Entries = testValues(1000).toMutableList()

        val actual = account.entriesInInterval(from = this.start.plusDays(500))

        assertEquals(expected, actual)
    }
    //endregion

    //region GetWithToParam
    @Test
    fun OneEntry_GetToLatestPossibleDate_ReturnOneEntry() {
        val expected = testValues(1)
        TestRepo.Entries = expected.toMutableList()

        val actual = account.entriesInInterval(to = DateTime.now())

        assertEquals(expected, actual)
    }

    @Test
    fun OneEntry_GetToDateBeforeEntry_ReturnEmptyList() {
        val expected = testValues(0)
        TestRepo.Entries = testValues(1).toMutableList()

        val actual = account.entriesInInterval(to = this.start.minusDays(1))

        assertEquals(expected, actual)
    }

    @Test
    fun MultipleEntries_GetToLatestPossibleDate_ReturnAllEntries() {
        val expected = testValues(1000)
        TestRepo.Entries = expected.toMutableList()

        val actual = account.entriesInInterval(to = DateTime.now())

        assertEquals(expected, actual)
    }

    @Test
    fun MultipleEntries_GetToDateWithinInterval_ReturnEntriesUpToThatDate() {
        val expected = testValues(1000).take(500)
        TestRepo.Entries = testValues(1000).toMutableList()

        val actual = account.entriesInInterval(to = this.start.plusDays(499))

        assertEquals(expected, actual)
    }
    //endregion

    //region GetWithBothParameters
    @Test
    fun MultipleEntries_GetFromEarliestToLatestPossibleDate_ReturnAllEntries() {
        val expected = testValues(1000)
        TestRepo.Entries = expected.toMutableList()

        val actual = account.entriesInInterval(from = this.start, to = this.start.plusDays(1000))

        assertEquals(expected, actual)
    }

    @Test
    fun MultipleEntries_GetFromInterval_ReturnEntriesInInterval() {
        val expected = testValues(1000).subList(300, 500)
        TestRepo.Entries = testValues(1000).toMutableList()

        val actual = account.entriesInInterval(
                from = this.start.plusDays(300),
                to = this.start.plusDays(499)
        )

        assertEquals(expected, actual)
    }

    @Test
    fun MultipleEntries_GetFromLaterDateToEarlierDate_ReturnEmptyList() {
        val expected = testValues(0)
        TestRepo.Entries = testValues(1000).toMutableList()

        val actual = account.entriesInInterval(
                from = this.start.plusDays(500),
                to = this.start.plusDays(300)
        )

        assertEquals(expected, actual)
    }
    //endregion

    //region AddOne
    @Test
    fun NoEntries_AddOne_ReturnOneEntry() {
        val date = this.start
        val expected = listOf(date)

        account.add(date)
        val actual = account.Entries

        assertEquals(expected, actual)
    }

    @Test
    fun OneEntry_AddOne_ReturnTwoEntries() {
        TestRepo.write(this.start)
        val expected = this.testValues(2)

        account.add(this.start.plusDays(1))
        val actual = account.Entries

        assertEquals(expected, actual)
    }

    @Test
    fun MultipleEntries_AddOne_ReturnMultiplePlusOneEntries() {
        this.testValues(1000)
                .forEach { TestRepo.write(it) }
        val expected = TestRepo.Entries
                .plus(this.start.plusDays(1000))

        account.add(this.start.plusDays(1000))
        val actual = account.Entries

        assertEquals(expected, actual)
    }
    //endregion

    //region AddMultiple
    @Test
    fun OneEntry_AddMultiple_ReturnOnePlusMultipleEntries() {
        TestRepo.write(this.start)
        val expected = this.testValues(1001)

        List(1000, { this.start.plusDays(it + 1) })
                .forEach { account.add(it) }
        val actual = account.Entries

        assertEquals(expected, actual)
    }

    @Test
    fun MultipleEntries_AddMultiple_ReturnMultiplePlusMultipleEntries() {
        this.testValues(1000)
                .forEach { TestRepo.write(it) }
        val expected = this.testValues(1000)
                .plus(TestRepo.Entries)

        this.testValues(1000)
                .forEach { account.add(it) }
        val actual = account.Entries

        assertEquals(expected, actual)
    }
    //endregion

    fun testValues(days: Int) = List(days, { this.start.plusDays(it) })
}
