package com.example.testapp.domain.useCases.notification


import com.example.testapp.data.entity.NotificationEntity
import com.example.testapp.data.repositories.notificationRepository.NotificationRepository
import java.time.ZoneId
import java.util.*
import javax.inject.Inject

class AddDefaultDayNightNotificationsUseCase @Inject constructor(
    private val notificationRepository: NotificationRepository
) {
    operator fun invoke() =
        notificationRepository.setNotifications(
            listOf(
                NotificationEntity(
                    id = System.currentTimeMillis(),
                    date = (GregorianCalendar.getInstance(TimeZone.getTimeZone(ZoneId.systemDefault())) as GregorianCalendar).apply {
                        set(GregorianCalendar.HOUR_OF_DAY, 9)
                        set(GregorianCalendar.MINUTE, 0)
                    },
                    dateId = 0,
                    isDay = true,
                    isActive = false
                ),
                NotificationEntity(
                    id = System.currentTimeMillis(),
                    date = (GregorianCalendar.getInstance(TimeZone.getTimeZone(ZoneId.systemDefault())) as GregorianCalendar).apply {
                        set(GregorianCalendar.HOUR_OF_DAY, 22)
                        set(GregorianCalendar.MINUTE, 30)
                    },
                    dateId = 0,
                    isDay = false,
                    isActive = false
                ),
            )
        )

}