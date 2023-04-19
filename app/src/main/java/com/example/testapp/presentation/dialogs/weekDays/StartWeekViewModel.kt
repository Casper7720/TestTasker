package com.example.testapp.presentation.dialogs.weekDays

import com.example.testapp.data.entity.AppInfoEntity
import com.example.testapp.domain.useCases.appInfo.GetAppInfoUseCase
import com.example.testapp.domain.useCases.appInfo.UpdateNextWeekUseCase
import com.example.testapp.domain.useCases.appInfo.UpdateStartWeekUseCase
import com.example.testapp.domain.useCases.appInfo.UpdateWeekendUseCase
import com.example.testapp.presentation.screens.fragments.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class StartWeekViewModel @Inject constructor(
    private val updateStartWeekUseCase: @JvmSuppressWildcards UpdateStartWeekUseCase,
    private val updateWeekendUseCase: @JvmSuppressWildcards UpdateWeekendUseCase,
    private val updateNextWeekUseCase: @JvmSuppressWildcards UpdateNextWeekUseCase,
    private val getAppInfoUseCase: @JvmSuppressWildcards GetAppInfoUseCase,
) : BaseViewModel() {

    private val _updateStartWeek = MutableUIStateFlow<Boolean>()
    val updateStartWeek = _updateStartWeek.asStateFlow()

    private val _updateNextWeek = MutableUIStateFlow<Boolean>()
    val updateNextWeek = _updateNextWeek.asStateFlow()

    private val _updateWeekend = MutableUIStateFlow<Boolean>()
    val updateWeekend = _updateWeekend.asStateFlow()

    private val _appInfo = MutableUIStateFlow<AppInfoEntity>()
    val appInfo = _appInfo.asStateFlow()


    fun getAppInfo() {
        getAppInfoUseCase().collectRequest(_appInfo) { it }
    }

    fun updateStartWeek(startWeek: Int) {
        updateStartWeekUseCase(startWeek).collectRequest(_updateStartWeek) { it }
    }

    fun updateWeekend(weekend: Int) {
        updateWeekendUseCase(weekend).collectRequest(_updateWeekend) { it }
    }

    fun updateNextWeek(nextWeek: Int) {
        updateNextWeekUseCase(nextWeek).collectRequest(_updateNextWeek) { it }
    }
}