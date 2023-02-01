package com.example.testapp.presentation.screens.activity.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.data.repositories.UserRepository
import com.example.testapp.presentation.models.LoadingState
import kotlinx.coroutines.launch

class MainActivityViewModel(private val userRepository: UserRepository): ViewModel() {
    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    val data = userRepository.data

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                _loadingState.value = LoadingState.LOADING
                userRepository.refresh()
                _loadingState.value = LoadingState.LOADED
            } catch (e: Exception) {
                _loadingState.value = LoadingState.error(e.message)
            }
        }
    }
}