package com.example.sebastiangebert.worktime.views.LogEntries

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.sebastiangebert.worktime.R
import com.example.sebastiangebert.worktime.viewmodels.TimePeriod

class LogEntryAdapter(val dataSource: TimePeriod) : RecyclerView.Adapter<LogEntryViewHolder>() {
    override fun onBindViewHolder(holder: LogEntryViewHolder?, position: Int) {
        if (holder == null) {
            return
        }

        val date = this.dataSource.All.elementAt(position)
        holder.view.text = date.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): LogEntryViewHolder {
        if (parent == null) {
            throw NullPointerException("The parent parameter must not be null.")
        }

        val layout = R.layout.log_item
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(layout, parent, false)

        return LogEntryViewHolder(view)
    }

    override fun getItemCount() = this.dataSource.All.size
}