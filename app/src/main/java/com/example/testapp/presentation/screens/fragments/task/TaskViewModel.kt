package com.example.testapp.presentation.screens.fragments.task

import com.example.testapp.domain.useCases.tasks.DeleteTaskUseCase
import com.example.testapp.domain.useCases.tasks.GetAllTasksUseCase
import com.example.testapp.presentation.models.TaskItem
import com.example.testapp.presentation.screens.fragments.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val getAllTasksUseCase: @JvmSuppressWildcards GetAllTasksUseCase,
    private val deleteTaskUseCase: @JvmSuppressWildcards DeleteTaskUseCase,
) : BaseViewModel() {

    private val _allTasks = MutableUIStateFlow<List<List<TaskItem>>>()
    val allTasks = _allTasks.asStateFlow()

    private val _deleteTask = MutableUIStateFlow<Boolean>()
    val deleteTask = _deleteTask.asStateFlow()

    fun getTasks() {
        getAllTasksUseCase().collectRequest(_allTasks) {
            val listTasks: MutableList<MutableList<TaskItem>> = mutableListOf(mutableListOf())
            val date = Calendar.getInstance()
            it.forEach { it ->
                if (it.date?.get(Calendar.DAY_OF_MONTH) == date.get(Calendar.DAY_OF_MONTH)) {
                    if(listTasks.size == 1){
                        listTasks.add(mutableListOf())
                    }
                    listTasks[1].add(it)
                }
                listTasks[0].add(it)

            }
            listTasks
        }
    }

    fun deleteTask(id: Long) {
        deleteTaskUseCase(id).collectRequest(_deleteTask) { it }
    }
}