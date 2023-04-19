package com.example.testapp.presentation.dialogs.weekDays

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.presentation.models.StringItem

class AdapterStrings : ListAdapter<StringItem, StringVH>(StringItemDU()) {

    private var list: List<StringItem> = ArrayList()
    private var listener: StringVH.ItemListener? = null

    fun setData(list: List<StringItem>) {
        this.list = list
        submitList(this.list)
    }

    fun setListener(listener: StringVH.ItemListener) {
        this.listener = listener
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringVH {
        return StringVH.create(parent)
    }

    override fun onBindViewHolder(holder: StringVH, position: Int) {
        return holder.bind(list[position], listener)
    }
}

class StringVH(val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: StringItem, listener: ItemListener?) {
        val title = view.findViewById<TextView>(R.id.title)

        title.text = item.text

        if (item.isActive) view.rootView.setBackgroundColor(
            ContextCompat.getColor(
                view.context,
                R.color.pink
            )
        ) else view.rootView.setBackgroundColor(
            ContextCompat.getColor(
                view.context,
                R.color.white
            )
        )


        view.rootView.setOnClickListener {
            listener?.onClick(item)
        }
    }

    interface ItemListener {
        fun onClick(item: StringItem)
    }

    companion object {
        fun create(parentView: ViewGroup): StringVH {
            return StringVH(
                LayoutInflater.from(parentView.context)
                    .inflate(R.layout.item_text, parentView, false)
            )
        }
    }
}

class StringItemDU : DiffUtil.ItemCallback<StringItem>() {
    override fun areItemsTheSame(oldItem: StringItem, newItem: StringItem): Boolean =
        oldItem.text == newItem.text

    override fun areContentsTheSame(oldItem: StringItem, newItem: StringItem): Boolean =
        oldItem.isActive == newItem.isActive
}
