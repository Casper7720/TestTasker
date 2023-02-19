package com.example.testapp.domain.useCases.tasks

import arrow.core.Either
import arrow.core.right
import com.example.testapp.data.repositories.tasksRepository.TasksRepository
import com.example.testapp.presentation.models.TaskItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllTasksUseCase @Inject constructor(
    private val tasksRepository: TasksRepository
) {
    operator fun invoke(): Flow<Either<String, List<TaskItem>>> {
        return tasksRepository.getAll()
    }

}