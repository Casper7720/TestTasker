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
    var allTasks = _allTasks.asStateFlow()
    var allTasksList: List<TaskItem> = listOf()

    private val _deleteTask = MutableUIStateFlow<Boolean>()
    val deleteTask = _deleteTask.asStateFlow()

    fun getTasks() {
        getAllTasksUseCase().collectRequest(_allTasks) {
            allTasksList =it
            val listTasks: MutableList<MutableList<TaskItem>> = mutableListOf(mutableListOf())
            val date = GregorianCalendar.getInstance()
            it.forEach { item ->
                if (item.date?.get(GregorianCalendar.DAY_OF_MONTH) == date.get(GregorianCalendar.DAY_OF_MONTH)) {
                    if(listTasks.size == 1){
                        listTasks.add(mutableListOf())
                    }
                    listTasks[1].add(item)
                }
                listTasks[0].add(item)

            }
            listTasks
        }
    }

    fun deleteTask(id: Long) {
        deleteTaskUseCase(id).collectRequest(_deleteTask) { it }
    }
}