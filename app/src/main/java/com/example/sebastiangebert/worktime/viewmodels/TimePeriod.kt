package com.example.sebastiangebert.worktime.viewmodels

import android.databinding.BaseObservable
import android.databinding.ObservableArrayList
import com.example.sebastiangebert.worktime.model.WorkTimeRepository
import org.joda.time.DateTime

class TimePeriod(val repository: WorkTimeRepository) : BaseObservable() {
    val All: ObservableArrayList<DateTime>
        get() {
            val entries = this.repository.readAll()
            val updatedList = ObservableArrayList<DateTime>()

            updatedList.addAll(entries)

            return updatedList
        }
}