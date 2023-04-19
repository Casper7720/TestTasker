package com.example.testapp.presentation.dialogs.mainScreenDialog

import com.example.testapp.data.entity.AppInfoEntity
import com.example.testapp.domain.useCases.appInfo.GetAppInfoUseCase
import com.example.testapp.domain.useCases.appInfo.UpdateMainScreenTypeUseCase
import com.example.testapp.presentation.screens.fragments.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainScreenDialogViewModel @Inject constructor(
    private val updateMainScreenTypeUseCase: @JvmSuppressWildcards UpdateMainScreenTypeUseCase,
    private val getAppInfoUseCase: @JvmSuppressWildcards GetAppInfoUseCase
) : BaseViewModel() {

    private val _updateMainScreen = MutableUIStateFlow<Boolean>()
    var updateMainScreen = _updateMainScreen.asStateFlow()

    private val _getAppInfo = MutableUIStateFlow<AppInfoEntity>()
    var getAppInfo = _getAppInfo.asStateFlow()

    fun getAppInfo() {
        getAppInfoUseCase().collectRequest(_getAppInfo) { it }
    }

    fun updateMainScreen(mainScreenType: Int) {
        updateMainScreenTypeUseCase(mainScreenType).collectRequest(_updateMainScreen) { it }
    }

}