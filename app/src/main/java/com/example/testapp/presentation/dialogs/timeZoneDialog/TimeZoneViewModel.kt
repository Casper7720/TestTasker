package com.example.testapp.presentation.dialogs.timeZoneDialog

import com.example.testapp.domain.useCases.appInfo.UpdateTimeZoneIdUseCase
import com.example.testapp.presentation.screens.fragments.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TimeZoneViewModel @Inject constructor(
    private val updateTimeZoneIdUseCase: @JvmSuppressWildcards UpdateTimeZoneIdUseCase
) : BaseViewModel() {

    private val _updateTimeZoneId = MutableUIStateFlow<Boolean>()
    val updateTimeZoneId = _updateTimeZoneId.asStateFlow()

    fun updateTimeZoneId(timeZoneId: String){
        updateTimeZoneIdUseCase(timeZoneId).collectRequest(_updateTimeZoneId){it}
    }

}