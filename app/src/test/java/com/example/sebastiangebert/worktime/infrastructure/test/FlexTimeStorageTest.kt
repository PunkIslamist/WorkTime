package com.example.sebastiangebert.worktime.infrastructure.test

import com.example.sebastiangebert.worktime.infrastructure.Database
import com.example.sebastiangebert.worktime.infrastructure.FlexTimeStorage
import org.joda.time.DateTime
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class FlexTimeStorageTest {
    companion object mockDB : Database {
        val entries: MutableCollection<String> = mutableListOf()

        override fun select() = entries

        override fun insert(values: Iterable<String>) {
            entries.addAll(values)
        }
    }

    @Before
    fun resetDB() = mockDB.entries.clear()

    @Test
    fun NoEntries_GetAllGetsNone() {
        val expected = arrayOf<DateTime>()

        val actual = FlexTimeStorage(mockDB).toTypedArray()

        Assert.assertArrayEquals(expected, actual)
    }

    @Test
    fun OneEntry_GetAllGetsOne() {
        val entry = DateTime(0)
        val expected = mutableListOf(entry).toTypedArray()
        mockDB.entries.add(entry.toString())

        val actual = FlexTimeStorage(mockDB).toTypedArray()

        Assert.assertArrayEquals(expected, actual)
    }

    @Test
    fun MultipleEntries_GetAllGetsAll() {
        val entries = List(123, { DateTime(it.toLong()) })
        val expected = entries.toTypedArray()
        mockDB.entries.addAll(entries.map { it.toString() })

        val actual = FlexTimeStorage(mockDB).toTypedArray()

        Assert.assertArrayEquals(expected, actual)
    }
}