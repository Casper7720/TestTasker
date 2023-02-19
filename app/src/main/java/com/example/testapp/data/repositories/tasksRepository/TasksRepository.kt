package com.example.testapp.data.repositories.tasksRepository

import arrow.core.Either
import com.example.testapp.data.entity.FavoriteChapterEntity
import com.example.testapp.data.entity.TaskEntity
import com.example.testapp.presentation.models.TaskItem
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
    fun getAll(): Flow<Either<String, List<TaskItem>>>
    fun addTask(taskEntity: TaskEntity): Flow<Either<String, Boolean>>
}