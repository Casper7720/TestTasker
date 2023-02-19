package com.example.testapp.domain.useCases.tasks

import arrow.core.Either
import com.example.testapp.data.entity.FavoriteChapterEntity
import com.example.testapp.data.entity.TaskEntity
import com.example.testapp.data.repositories.favoriteChapterRepository.FavoriteChapterRepository
import com.example.testapp.data.repositories.tasksRepository.TasksRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(
    private val tasksRepository: TasksRepository
) {
    operator fun invoke(taskEntity: TaskEntity): Flow<Either<String, Boolean>> =
        tasksRepository.addTask(taskEntity)
}