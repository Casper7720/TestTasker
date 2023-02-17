package com.example.testapp.presentation.screens.fragments.task


import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testapp.R
import com.example.testapp.databinding.FragmentTaskBinding
import com.example.testapp.presentation.screens.fragments.base.BaseFragment

class TaskFragment : BaseFragment<TaskViewModel, FragmentTaskBinding>(
    R.layout.fragment_task
) {

    override val viewModel: TaskViewModel by viewModels()
    override val binding: FragmentTaskBinding by viewBinding(FragmentTaskBinding::bind)


    override fun initialize() {
        super.initialize()
    }

    override fun setupListeners() {
        super.setupListeners()
    }

    override fun setupRequests() {
        super.setupRequests()
    }

    override fun setupSubscribers() {
        super.setupSubscribers()
    }
}