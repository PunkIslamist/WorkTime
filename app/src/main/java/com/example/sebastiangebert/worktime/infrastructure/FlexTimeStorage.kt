package com.example.sebastiangebert.worktime.infrastructure

import org.joda.time.DateTime

class FlexTimeStorage(private val database: Database) : FlexTimeRepository {
    private val cache = this.database.select()
            .map { DateTime(it) }
            .toMutableList()

    override val size get() = this.cache.size

    override fun contains(element: DateTime) = this.cache.contains(element)

    override fun containsAll(elements: Collection<DateTime>) = this.cache.containsAll(elements)

    override fun isEmpty() = this.cache.isEmpty()

    override fun add(element: DateTime): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addAll(elements: Collection<DateTime>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clear() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun iterator() = this.cache.iterator()

    override fun remove(element: DateTime): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeAll(elements: Collection<DateTime>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun retainAll(elements: Collection<DateTime>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
