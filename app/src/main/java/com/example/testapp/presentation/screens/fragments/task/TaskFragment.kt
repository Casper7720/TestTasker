package com.example.testapp.presentation.screens.fragments.task


import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testapp.R
import com.example.testapp.databinding.FragmentTaskBinding
import com.example.testapp.presentation.AlarmReceiver
import com.example.testapp.presentation.dialogs.addTask.AddTaskDialog
import com.example.testapp.presentation.models.TaskItem
import com.example.testapp.presentation.screens.fragments.base.BaseFragment
import com.example.testapp.presentation.screens.fragments.task.adapter.TasksVH
import com.example.testapp.presentation.screens.fragments.task.adapter.TasksViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.concurrent.Task


@AndroidEntryPoint
class TaskFragment : BaseFragment<TaskViewModel, FragmentTaskBinding>(
    R.layout.fragment_task
){

    override val viewModel: TaskViewModel by viewModels()
    override val binding: FragmentTaskBinding by viewBinding(FragmentTaskBinding::bind)

    private lateinit var adapterViewPager: TasksViewPagerAdapter

    override fun initialize() {
        super.initialize()
        binding.toolbar.apply {
            setNavigationIcon(R.drawable.ic_back)
            title = getString(R.string.task_toolbar_title)
            setNavigationOnClickListener {
                getRouter().exit()
            }
            inflateMenu(R.menu.menu_tasks)
        }
        adapterViewPager = TasksViewPagerAdapter(requireContext(), listOf())
        adapterViewPager.setTaskListener(object : TasksVH.TaskItemListener {
            override fun delete(id: Long) {

                val item = viewModel.allTasksList.first { item -> item.id == id }
                if(item.date != null) removeAlarm(item)
                viewModel.deleteTask(id)

            }

        })
        binding.tasksVp.apply {
            adapter = adapterViewPager
        }
        binding.tabLayout.setupWithViewPager(binding.tasksVp)

        viewModel.getTasks()

        setFragmentResultListener("REQUEST_ADD_TASK"){key, bundle ->
            val data = bundle.getBoolean("add_task")
            if(data){
                viewModel.getTasks()
            }

        }
    }

    private fun setDataViewPager(list: List<List<TaskItem>>) {
        adapterViewPager.setData(list)
    }

    override fun setupListeners() {
        super.setupListeners()

        binding.addNewTask.setOnClickListener {
            val dialog = AddTaskDialog()
            dialog.show(parentFragmentManager, "addTask")
        }
    }

    private fun removeAlarm(item: TaskItem){
        val alarmManager = activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(requireContext(), AlarmReceiver::class.java)
        intent.putExtra(AlarmReceiver.ALARM_RECEIVER_ID, item.id)
        intent.putExtra(AlarmReceiver.ALARM_RECEIVER_TITLE, item.text)
        intent.putExtra(AlarmReceiver.ALARM_RECEIVER_DESCRIPTION, "Необходимо выполнить задачу")

        val pendingIntent = PendingIntent.getBroadcast(
            requireContext(),
            item.id.toInt(),
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.cancel(pendingIntent)
    }


    override fun setupRequests() {
        super.setupRequests()
    }

    override fun setupSubscribers() {
        super.setupSubscribers()

        viewModel.allTasks.collectUIState(
            state = {},
            onError = {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            },
            onSuccess = {
                setDataViewPager(it)
            })

        viewModel.deleteTask.collectUIState(
            state = {},
            onError = {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            },
            onSuccess = {
                viewModel.getTasks()
            }
        )
    }
}
