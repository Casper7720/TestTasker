package com.example.testapp.data.repositories.tasksRepository

import arrow.core.Either
import com.example.testapp.data.dao.TasksDao
import com.example.testapp.data.entity.TaskEntity
import com.example.testapp.data.repositories.BaseRepository
import com.example.testapp.presentation.models.TaskItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TasksRepositoryImpl @Inject constructor(
    private val tasksDao: TasksDao
) : TasksRepository, BaseRepository() {
    override fun getAll(): Flow<Either<String, List<TaskItem>>> =
        doRequest {
            tasksDao.findAll().map {
                TaskItem(
                    it.id,
                    it.title,
                    it.date
                )
            }
        }

    override fun addTask(taskEntity: TaskEntity): Flow<Either<String, Boolean>> =
        doRequest {
            tasksDao.add(taskEntity)
            true
        }

}