package com.example.testapp.presentation.screens.fragments.task

import com.example.testapp.data.entity.TaskEntity
import com.example.testapp.domain.useCases.tasks.AddTaskUseCase
import com.example.testapp.domain.useCases.tasks.GetAllTasksUseCase
import com.example.testapp.presentation.models.TaskItem
import com.example.testapp.presentation.screens.fragments.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val addTaskUseCase: @JvmSuppressWildcards AddTaskUseCase,
    private val getAllTasksUseCase: @JvmSuppressWildcards GetAllTasksUseCase
) : BaseViewModel() {

    private val _addTasks = MutableUIStateFlow<Boolean>()
    val addTask = _addTasks.asStateFlow()

    private val _allTasks = MutableUIStateFlow<List<TaskItem>>()
    val allTasks = _allTasks.asStateFlow()

    fun getTasks() {
        getAllTasksUseCase().collectRequest(_allTasks) { it }
    }

    fun addTask(id: Long, title: String, date: Date) {
        addTaskUseCase(
            TaskEntity(
                id, title, date
            )
        ).collectRequest(_addTasks) { it }
    }
}