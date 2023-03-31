package com.example.testapp.domain.useCases.notification

import arrow.core.Either
import com.example.testapp.data.repositories.notificationRepository.NotificationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateActiveDayNightNotificationUseCase @Inject constructor(
    private val notificationRepository: NotificationRepository
) {
    operator fun invoke(isDay: Boolean, isActive: Boolean): Flow<Either<String, Boolean>> =
        notificationRepository.updateActive(isDay, isActive)

}