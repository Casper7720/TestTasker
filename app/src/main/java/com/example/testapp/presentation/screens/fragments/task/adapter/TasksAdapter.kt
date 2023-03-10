package com.example.testapp.presentation.screens.fragments.task.adapter

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.work.WorkManager
import com.example.testapp.R
import com.example.testapp.presentation.models.TaskItem

class TasksAdapter : ListAdapter<TaskItem, TasksVH>(TaskItemDU()) {

    private var list: List<TaskItem> = ArrayList()
    private var listener: TasksVH.TaskItemListener? = null

    fun setData(list: List<TaskItem>) {
        this.list = list
        submitList(this.list)
    }

    fun setListener(listener: TasksVH.TaskItemListener) {
        this.listener = listener
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksVH {
        return TasksVH.create(parent)
    }

    override fun onBindViewHolder(holder: TasksVH, position: Int) {
        return holder.bind(list[position], listener)
    }
}

class TasksVH(val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: TaskItem, listener: TaskItemListener?) {
        val title = view.findViewById<TextView>(R.id.task_title)
        val checkBox = view.findViewById<CheckBox>(R.id.tasks_cb)
        val date = view.findViewById<TextView>(R.id.task_date)

        val flags = DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_TIME

        title.text = item.text
        date.text = DateUtils.formatDateTime(
            view.context,
            item.date?.timeInMillis ?: 0L,
            flags
        )

        checkBox.setOnClickListener {
            listener?.delete(item.id)
            if (item.notificationTag != null) {
                WorkManager.getInstance(view.context).cancelAllWorkByTag(item.notificationTag ?: "")
            }
        }
    }

    interface TaskItemListener {
        fun delete(id: Long)
    }

    companion object {
        fun create(parentView: ViewGroup): TasksVH {
            return TasksVH(
                LayoutInflater.from(parentView.context)
                    .inflate(R.layout.item_task, parentView, false)
            )
        }
    }
}

class TaskItemDU : DiffUtil.ItemCallback<TaskItem>() {
    override fun areItemsTheSame(oldItem: TaskItem, newItem: TaskItem): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: TaskItem, newItem: TaskItem): Boolean =
        oldItem.id == newItem.id

}