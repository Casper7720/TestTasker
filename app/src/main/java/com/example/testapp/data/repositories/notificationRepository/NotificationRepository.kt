package com.example.testapp.data.repositories.notificationRepository

import arrow.core.Either
import com.example.testapp.data.entity.NotificationEntity
import com.example.testapp.data.repositories.BaseRepository
import kotlinx.coroutines.flow.Flow
import java.util.GregorianCalendar

interface NotificationRepository {
    fun getNotifications(): Flow<Either<String, List<NotificationEntity>>>
    fun setNotification(notification: NotificationEntity) : Flow<Either<String, Boolean>>
    fun setNotifications(notifications: List<NotificationEntity>) : Flow<Either<String, Unit>>
    fun updateActive(isDay: Boolean, isActive: Boolean): Flow<Either<String, Boolean>>
    fun updateDayNightNotificationDate(isDay: Boolean, date: GregorianCalendar, dateId: Long): Flow<Either<String, Boolean>>
    fun deleteNotificationById(isDay: Boolean): Flow<Either<String, Boolean>>
}