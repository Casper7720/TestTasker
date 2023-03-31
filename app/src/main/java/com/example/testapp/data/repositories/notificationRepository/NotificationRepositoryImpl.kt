package com.example.testapp.data.repositories.notificationRepository

import arrow.core.Either
import com.example.testapp.data.dao.NotificationDao
import com.example.testapp.data.entity.NotificationEntity
import com.example.testapp.data.repositories.BaseRepository
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val notificationDao: NotificationDao
) : NotificationRepository, BaseRepository() {
    override fun getNotifications(): Flow<Either<String, List<NotificationEntity>>> = doRequest {
        notificationDao.findAll()
    }

    override fun setNotification(notification: NotificationEntity): Flow<Either<String, Boolean>> =
        doRequest {
            notificationDao.add(notification)
            true
        }

    override fun setNotifications(notifications: List<NotificationEntity>): Flow<Either<String, Unit>> =
        doRequest {
            notificationDao.add(notifications)
        }


    override fun updateDayNightNotificationDate(
        isDay: Boolean,
        date: GregorianCalendar,
        dateId: Long
    ): Flow<Either<String, Boolean>> =
        doRequest {
            notificationDao.updateDate(isDay, date, dateId)
            true
        }

    override fun updateActive(isDay: Boolean, isActive: Boolean): Flow<Either<String, Boolean>> =
        doRequest {
            notificationDao.updateActive(isDay, isActive)
            true
        }

    override fun deleteNotificationById(isDay: Boolean): Flow<Either<String, Boolean>> = doRequest {
        notificationDao.deleteByTag(isDay)
        true
    }
}