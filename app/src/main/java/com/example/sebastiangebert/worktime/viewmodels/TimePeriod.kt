package com.example.sebastiangebert.worktime.viewmodels

import android.databinding.BaseObservable
import android.databinding.ObservableArrayList
import com.example.sebastiangebert.worktime.infrastructure.FlexTimeRepository
import org.joda.time.DateTime

class TimePeriod(private val repository: FlexTimeRepository) : BaseObservable() {
    val All: ObservableArrayList<DateTime>
        get() {
            val entries = this.repository
                    .filter { it >= this.Start && it <= this.End }
            val updatedList = ObservableArrayList<DateTime>()

            updatedList.addAll(entries)
            return updatedList
        }

    var Start: DateTime = this.repository.let {
        if (it.any())
            it.first()
        else DateTime(0)
    }

    var End: DateTime = this.repository.let {
        if (it.any())
            it.last()
        else DateTime(0)
    }
}