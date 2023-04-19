package com.example.testapp.presentation.dialogs.timeZoneDialog.adapter

import android.icu.util.TimeZone
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.presentation.models.TimeZoneItem

class TimeZoneAdapter : ListAdapter<TimeZoneItem, TimeZoneVH>(TimeZoneItemDU()) {

    private var list: List<TimeZoneItem> = ArrayList()
    private var listener: TimeZoneVH.TimeZoneListener? = null

    fun setData(list: List<TimeZoneItem>) {
        this.list = list
        submitList(this.list)
    }

    fun setListener(listener: TimeZoneVH.TimeZoneListener) {
        this.listener = listener
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeZoneVH {
        return TimeZoneVH.create(parent)
    }

    override fun onBindViewHolder(holder: TimeZoneVH, position: Int) {
        return holder.bind(list[position], listener)
    }
}

class TimeZoneVH(val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: TimeZoneItem, listener: TimeZoneListener?) {
        val name = view.findViewById<TextView>(R.id.title)

        val appTimeZone = TimeZone.getDefault()
        if (appTimeZone.id == item.timeZoneId) view.rootView.setBackgroundColor(
            ContextCompat.getColor(
                view.context,
                R.color.pink
            )
        ) else view.rootView.setBackgroundColor(ContextCompat.getColor(view.context, R.color.white))
        name.text = item.timeZoneId

        view.rootView.setOnClickListener {
            listener?.onClick(item.timeZoneId)
        }
    }

    interface TimeZoneListener {
        fun onClick(language: String)
    }

    companion object {
        fun create(parentView: ViewGroup): TimeZoneVH {
            return TimeZoneVH(
                LayoutInflater.from(parentView.context)
                    .inflate(R.layout.item_text, parentView, false)
            )
        }
    }
}

class TimeZoneItemDU : DiffUtil.ItemCallback<TimeZoneItem>() {
    override fun areItemsTheSame(oldItem: TimeZoneItem, newItem: TimeZoneItem): Boolean =
        oldItem.isActive == newItem.isActive

    override fun areContentsTheSame(oldItem: TimeZoneItem, newItem: TimeZoneItem): Boolean =
        oldItem.timeZoneId == newItem.timeZoneId

}