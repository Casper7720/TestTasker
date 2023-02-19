package com.example.testapp.presentation.screens.fragments.task.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.example.testapp.R
import com.example.testapp.presentation.models.TaskItem


class TasksViewPagerAdapter(
    val context: Context,
    var tasks: List<List<TaskItem>>
    ) : PagerAdapter() {

    private lateinit var adapter: TasksAdapter

    fun setData(list: List<List<TaskItem>>){
        this.tasks = list
        notifyDataSetChanged()
    }

    override fun getCount(): Int = tasks.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val taskRv: RecyclerView
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView: View = inflater.inflate(R.layout.item_tasks_rv, container, false)

        adapter = TasksAdapter()
        adapter.setData(tasks[position])

        taskRv = itemView.findViewById(R.id.tasks_rv)

        taskRv.layoutManager = LinearLayoutManager(context)
        taskRv.adapter = adapter


        container.addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}