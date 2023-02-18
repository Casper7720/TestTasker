package com.example.testapp.presentation.screens.activity.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.example.testapp.presentation.App
import com.example.testapp.presentation.models.UIState
import com.example.testapp.presentation.screens.fragments.base.BaseViewModel
import com.github.terrakok.cicerone.Router
import com.google.android.material.progressindicator.CircularProgressIndicator
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseActivity<ViewModel : BaseViewModel, Binding : ViewBinding>(
    @LayoutRes layoutId: Int
) : AppCompatActivity(layoutId) {

    protected abstract val viewModel: ViewModel
    protected abstract val binding: Binding

    fun getRouter(): Router = App.INSTANCE.router

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()
        setupListeners()
        setupRequests()
        setupSubscribers()
    }

    protected open fun initialize() {
    }

    protected open fun setupListeners() {
    }

    protected open fun setupRequests() {
    }

    protected open fun setupSubscribers() {
    }

    /**
     * Collect flow safely with [repeatOnLifecycle] API
     */
    protected fun collectFlowSafely(
        lifecycleState: Lifecycle.State,
        collect: suspend () -> Unit
    ) {
        lifecycleScope.launch {
            repeatOnLifecycle(lifecycleState) {
                collect()
            }
        }
    }

    /**
     * Collect [UIState] with [collectFlowSafely] and optional states params
     * @param state for working with all states
     * @param onError for error handling
     * @param onSuccess for working with data
     */
    protected fun <T> StateFlow<UIState<T>>.collectUIState(
        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
        state: ((UIState<T>) -> Unit)? = null,
        onError: ((error: String) -> Unit),
        onSuccess: ((data: T) -> Unit)
    ) {
        collectFlowSafely(lifecycleState) {
            this.collect {
                state?.invoke(it)
                when (it) {
                    is UIState.Idle -> {}
                    is UIState.Loading -> {}
                    is UIState.Error -> onError.invoke(it.error)
                    is UIState.Success -> onSuccess.invoke(it.data)
                }
            }
        }
    }

    protected fun <T> UIState<T>.setupViewVisibility(
        group: Group, loader: CircularProgressIndicator, isNavigateWhenSuccess: Boolean = false
    ) {
        fun showLoader(isVisible: Boolean) {
            group.isVisible = !isVisible
            loader.isVisible = isVisible
        }

        when (this) {
            is UIState.Idle -> {}
            is UIState.Loading -> showLoader(true)
            is UIState.Error -> showLoader(false)
            is UIState.Success -> if (!isNavigateWhenSuccess) showLoader(false)
        }
    }
}