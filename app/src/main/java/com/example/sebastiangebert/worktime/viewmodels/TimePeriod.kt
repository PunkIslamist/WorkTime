package com.example.sebastiangebert.worktime.viewmodels

import com.example.sebastiangebert.worktime.infrastructure.FlexTimeRepository
import org.joda.time.DateTime

class TimePeriod(private val repository: FlexTimeRepository) {
    val All get() = this.repository
            .filter { it >= this.Start && it <= this.End }

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