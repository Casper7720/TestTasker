package com.example.testapp.presentation.screens.activity.main

import androidx.lifecycle.ViewModel
import com.example.testapp.domain.interactors.AddDefaultValueInteractor
import com.example.testapp.presentation.models.ChapterItem
import com.example.testapp.presentation.models.UIState
import com.example.testapp.presentation.screens.fragments.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val addDefaultValueInteractor: @JvmSuppressWildcards AddDefaultValueInteractor
): BaseViewModel() {

    private val _defaultData = MutableUIStateFlow<Boolean>()
    val defaultData = _defaultData.asStateFlow()

    fun addDefaultData(){
        addDefaultValueInteractor().collectRequest(_defaultData){it}
    }
}