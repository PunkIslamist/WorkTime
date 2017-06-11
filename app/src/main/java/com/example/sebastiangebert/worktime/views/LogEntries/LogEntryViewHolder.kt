package com.example.sebastiangebert.worktime.views.LogEntries

import android.support.v7.widget.RecyclerView
import com.example.sebastiangebert.worktime.R

class LogEntryViewHolder(viewLayout: android.view.View) : RecyclerView.ViewHolder(viewLayout) {
    var view = viewLayout.findViewById(R.id.value) as android.widget.TextView
}