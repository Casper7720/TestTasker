package com.example.testapp.presentation.screens.activity.main


import com.example.testapp.domain.useCases.AddDefaultChaptersUseCase
import com.example.testapp.domain.useCases.notification.AddDefaultDayNightNotificationsUseCase
import com.example.testapp.presentation.screens.fragments.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val addDefaultDayNightNotificationsUseCase: @JvmSuppressWildcards AddDefaultDayNightNotificationsUseCase,
    private val addDefaultChaptersUseCase: @JvmSuppressWildcards AddDefaultChaptersUseCase,
): BaseViewModel() {

    private val _defaultDayNightNotifications = MutableUIStateFlow<Unit>()
    val defaultDayNightNotifications = _defaultDayNightNotifications.asStateFlow()

    private val _defaultChapters = MutableUIStateFlow<Unit>()
    val defaultChapters = _defaultChapters.asStateFlow()

    fun addDefaultData(){
        addDefaultChaptersUseCase().collectRequest(_defaultChapters){it}
        addDefaultDayNightNotificationsUseCase().collectRequest(_defaultDayNightNotifications){it}
    }
}