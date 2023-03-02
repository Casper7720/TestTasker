package com.example.testapp.domain.useCases.tasks

import arrow.core.Either
import com.example.testapp.data.entity.TaskEntity
import com.example.testapp.data.repositories.tasksRepository.TasksRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val tasksRepository: TasksRepository
) {
    operator fun invoke(id: Long): Flow<Either<String, Boolean>> =
        tasksRepository.deleteTask(id)
}