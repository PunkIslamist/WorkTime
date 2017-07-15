package com.example.sebastiangebert.worktime.infrastructure

import org.joda.time.DateTime

class FlexTimeStorage(private val database: Database) : FlexTimeRepository {
    override val size: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun contains(element: DateTime): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun containsAll(elements: Collection<DateTime>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isEmpty(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun add(element: DateTime): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addAll(elements: Collection<DateTime>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clear() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun iterator(): MutableIterator<DateTime> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun remove(element: DateTime): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeAll(elements: Collection<DateTime>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun retainAll(elements: Collection<DateTime>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun asDateTime(value: String): DateTime {
        val timestamp = this.asUnixTimestamp(value)

        return DateTime(timestamp)
    }

    /**
     * Returns the value as a unix timestamp value.
     * SQLite saves the unix timestamp without milliseconds,
     * but Joda-Time expects a millisecond resolution.
     */
    private fun asUnixTimestamp(value: String): Long {
        return value.toLong() * 1000
    }

    private fun DateTime.toUnixTime() = this.millis / 1000
}
