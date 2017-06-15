package com.example.sebastiangebert.worktime.viewmodels

import android.databinding.BaseObservable
import android.databinding.ObservableArrayList
import com.example.sebastiangebert.worktime.infrastructure.WorkTimeRepository
import org.joda.time.DateTime

class TimePeriod(private val repository: WorkTimeRepository) : BaseObservable() {
    val All: ObservableArrayList<DateTime>
        get() {
            val entries = this.repository
                    .readAll()
                    .filter { it >= this.Start && it < this.End }
            val updatedList = ObservableArrayList<DateTime>()

            updatedList.addAll(entries)

            return updatedList
        }

    var Start = this.repository.readAll().first()
    var End = this.repository.readAll().last().plusSeconds(1)
}