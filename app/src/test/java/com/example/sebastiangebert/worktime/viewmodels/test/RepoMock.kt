package com.example.sebastiangebert.worktime.viewmodels.test

import com.example.sebastiangebert.worktime.infrastructure.FlexTimeRepository
import org.joda.time.DateTime

class RepoMock(var entries: MutableList<DateTime>) : FlexTimeRepository {
    override val size get() = entries.size

    override fun contains(element: DateTime) = entries.contains(element)

    override fun containsAll(elements: Collection<DateTime>) = entries.containsAll(elements)

    override fun isEmpty() = entries.isEmpty()

    override fun add(element: DateTime) = entries.add(element)

    override fun addAll(elements: Collection<DateTime>) = entries.addAll(elements)

    override fun clear() = entries.clear()

    override fun iterator() = entries.iterator()

    override fun remove(element: DateTime) = entries.remove(element)

    override fun removeAll(elements: Collection<DateTime>) = entries.removeAll(elements)

    override fun retainAll(elements: Collection<DateTime>) = entries.retainAll(elements)
}