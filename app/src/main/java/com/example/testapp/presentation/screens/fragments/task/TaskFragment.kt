package com.example.testapp.presentation.screens.fragments.task


import android.widget.Toast
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testapp.R
import com.example.testapp.databinding.FragmentTaskBinding
import com.example.testapp.presentation.models.TaskItem
import com.example.testapp.presentation.screens.fragments.base.BaseFragment
import com.example.testapp.presentation.screens.fragments.task.adapter.TasksViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.time.Instant
import java.util.*

@AndroidEntryPoint
class TaskFragment : BaseFragment<TaskViewModel, FragmentTaskBinding>(
    R.layout.fragment_task
) {

    override val viewModel: TaskViewModel by viewModels()
    override val binding: FragmentTaskBinding by viewBinding(FragmentTaskBinding::bind)

    private lateinit var adapterViewPager: TasksViewPagerAdapter


    override fun initialize() {
        super.initialize()
        binding.toolbar.apply {
            setNavigationIcon(R.drawable.ic_back)
            setNavigationOnClickListener {
                getRouter().exit()
            }
            inflateMenu(R.menu.menu_tasks)
        }
        adapterViewPager = TasksViewPagerAdapter(requireContext(), listOf())
        binding.tasksVp.apply {
            adapter = adapterViewPager
        }

        viewModel.getTasks()

    }

    private fun setDataViewPager(list: List<List<TaskItem>>){
        adapterViewPager.setData(list)
    }

    override fun setupListeners() {
        super.setupListeners()

        binding.addNewTask.setOnClickListener {
            viewModel.addTask(
                0,
                "2134",
                Date.from(Instant.now())
            )
            viewModel.getTasks()
        }
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
                setDataViewPager(listOf(it))
            })
    }
}