package com.example.testapp.presentation.screens.activity.main


import com.example.testapp.data.entity.AppInfoEntity
import com.example.testapp.domain.useCases.AddDefaultChaptersUseCase
import com.example.testapp.domain.useCases.appInfo.AddAppInfoUseCase
import com.example.testapp.domain.useCases.appInfo.GetAppInfoUseCase
import com.example.testapp.domain.useCases.appInfo.UpdateTimeZoneIdUseCase
import com.example.testapp.domain.useCases.notification.AddDefaultDayNightNotificationsUseCase
import com.example.testapp.presentation.screens.fragments.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val addDefaultDayNightNotificationsUseCase: @JvmSuppressWildcards AddDefaultDayNightNotificationsUseCase,
    private val addDefaultChaptersUseCase: @JvmSuppressWildcards AddDefaultChaptersUseCase,
    private val addAppInfoUseCase: @JvmSuppressWildcards AddAppInfoUseCase,
    private val getAppInfoUseCase: @JvmSuppressWildcards GetAppInfoUseCase,
    private val updateTimeZoneIdUseCase: @JvmSuppressWildcards UpdateTimeZoneIdUseCase
) : BaseViewModel() {

    private val _defaultDayNightNotifications = MutableUIStateFlow<Unit>()
    val defaultDayNightNotifications = _defaultDayNightNotifications.asStateFlow()

    private val _defaultChapters = MutableUIStateFlow<Unit>()
    val defaultChapters = _defaultChapters.asStateFlow()

    private val _addAppInfo = MutableUIStateFlow<Boolean>()
    val addAppInfo = _addAppInfo.asStateFlow()

    private val _appInfo = MutableUIStateFlow<AppInfoEntity>()
    val appInfo = _appInfo.asStateFlow()

    fun addDefaultData() {
        addDefaultChaptersUseCase().collectRequest(_defaultChapters) { it }
        addDefaultDayNightNotificationsUseCase().collectRequest(_defaultDayNightNotifications) { it }
        addAppInfoUseCase(
            AppInfoEntity(
                id = System.currentTimeMillis(),
                mainScreenType = 0,
                timeZoneId = TimeZone.getDefault().id,
                startWeek = 0,
                nextWeek = 0,
                weekend = 6,

            )
        ).collectRequest(_addAppInfo) { it }
    }

    fun getAppInfo() {
        getAppInfoUseCase().collectRequest(_appInfo) { it }
    }
}