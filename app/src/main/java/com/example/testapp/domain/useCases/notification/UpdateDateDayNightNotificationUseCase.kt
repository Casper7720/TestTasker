package com.example.testapp.domain.useCases.notification

import arrow.core.Either
import com.example.testapp.data.repositories.notificationRepository.NotificationRepository
import kotlinx.coroutines.flow.Flow
import java.util.GregorianCalendar
import javax.inject.Inject

class UpdateDateDayNightNotificationUseCase @Inject constructor(
    private val notificationRepository: NotificationRepository
) {
    operator fun invoke(isDay: Boolean, date: GregorianCalendar, dateId: Long): Flow<Either<String, Boolean>> =
        notificationRepository.updateDayNightNotificationDate(isDay, date, dateId)
}