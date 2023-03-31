package com.example.testapp.presentation.screens.fragments.dayNightNotification

import com.example.testapp.data.entity.NotificationEntity
import com.example.testapp.domain.useCases.notification.GetNotificationsUseCase
import com.example.testapp.domain.useCases.notification.UpdateActiveDayNightNotificationUseCase
import com.example.testapp.domain.useCases.notification.UpdateDateDayNightNotificationUseCase
import com.example.testapp.presentation.screens.fragments.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DayNightNotificationViewModel @Inject constructor(
    private val getNotificationsUseCase: @JvmSuppressWildcards GetNotificationsUseCase,
    private val updateDateDayNightNotificationUseCase: @JvmSuppressWildcards UpdateDateDayNightNotificationUseCase,
    private val updateActiveDayNightNotificationUseCase: @JvmSuppressWildcards UpdateActiveDayNightNotificationUseCase
) : BaseViewModel() {

    private val _notifications = MutableUIStateFlow<List<NotificationEntity>>()
    val notifications = _notifications.asStateFlow()
    var notificationList: List<NotificationEntity> = listOf()

    private val _updateDate = MutableUIStateFlow<Boolean>()
    val updateDate = _updateDate.asStateFlow()

    private val _updateActive = MutableUIStateFlow<Boolean>()
    val updateActive = _updateActive.asStateFlow()


    fun getNotifications() {
        getNotificationsUseCase().collectRequest(_notifications) {
            notificationList = it
            it
        }
    }

    fun updateDate(isDay: Boolean, date: GregorianCalendar, dateId: Long) {
        updateDateDayNightNotificationUseCase(isDay, date, dateId).collectRequest(_updateDate) { it }
    }

    fun updateActive(isDay: Boolean, isActive: Boolean ){
        updateActiveDayNightNotificationUseCase(isDay, isActive).collectRequest(_updateActive) { it }
    }
}