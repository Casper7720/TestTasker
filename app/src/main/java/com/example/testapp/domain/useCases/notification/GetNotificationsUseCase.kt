package com.example.testapp.domain.useCases.notification

import arrow.core.Either
import com.example.testapp.data.entity.NotificationEntity
import com.example.testapp.data.repositories.notificationRepository.NotificationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNotificationsUseCase @Inject constructor(
    private val notificationRepository: NotificationRepository
) {
    operator fun invoke(): Flow<Either<String, List<NotificationEntity>>> =
        notificationRepository.getNotifications()
}