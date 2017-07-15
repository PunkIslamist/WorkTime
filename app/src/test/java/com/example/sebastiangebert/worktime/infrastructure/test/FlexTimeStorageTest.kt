package com.example.sebastiangebert.worktime.infrastructure.test

import com.example.sebastiangebert.worktime.infrastructure.Database
import com.example.sebastiangebert.worktime.infrastructure.FlexTimeStorage
import org.joda.time.DateTime
import org.junit.Assert
import org.junit.Test

class FlexTimeStorageTest {
    class MockDataBase(private val entries: MutableCollection<String>) : Database {
        override fun select() = entries

        override fun insert(values: Iterable<String>) {
            entries.addAll(values)
        }

    }

    @Test
    fun OneEntry_GetAllGetsOne() {
        val entry = DateTime(0)
        val testInstance = FlexTimeStorage(MockDataBase(mutableListOf(entry.toString())))
        val expected = mutableListOf(entry).toTypedArray()

        val actual = testInstance.toTypedArray()

        Assert.assertArrayEquals(expected, actual)
    }
}