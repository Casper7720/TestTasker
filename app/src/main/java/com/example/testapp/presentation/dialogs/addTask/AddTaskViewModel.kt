package com.example.testapp.presentation.dialogs.addTask

import com.example.testapp.data.entity.TaskEntity
import com.example.testapp.domain.useCases.tasks.AddTaskUseCase
import com.example.testapp.presentation.screens.fragments.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val addTaskUseCase: @JvmSuppressWildcards AddTaskUseCase,
) : BaseViewModel() {

    private val _addTasks = MutableUIStateFlow<Boolean>()
    val addTask = _addTasks.asStateFlow()

    fun addTask(id: Long, title: String, date: Calendar?) {
        addTaskUseCase(
            TaskEntity(
                id, title, date
            )
        ).collectRequest(_addTasks) { it }
    }
}