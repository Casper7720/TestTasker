package com.example.testapp.presentation.screens.activity.main

import android.content.Context
import android.widget.Toast
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testapp.R
import com.example.testapp.databinding.ActivityMainBinding
import com.example.testapp.presentation.App
import com.example.testapp.presentation.Screens
import com.example.testapp.presentation.screens.activity.base.BaseActivity
import com.github.terrakok.cicerone.androidx.AppNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>(
    R.layout.activity_main
) {

    override val viewModel: MainActivityViewModel by viewModels()
    override val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)

    private val navigator = AppNavigator(this, R.id.fragment_container)

    override fun initialize() {
        super.initialize()

        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        val firstStep = sharedPref.getInt(getString(R.string.first_step), 1)
        if (firstStep == 1) {
            viewModel.addDefaultData()
            sharedPref.edit().putInt(getString(R.string.first_step), firstStep + 1).apply()
        }

        getRouter().navigateTo(Screens.chapter())
    }

    override fun setupListeners() {
        super.setupListeners()
    }

    override fun setupRequests() {
        super.setupRequests()
    }

    override fun setupSubscribers() {
        super.setupSubscribers()

        viewModel.defaultChapters.collectUIState(
            state = {},
            onSuccess = {},
            onError = {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        )

        viewModel.defaultDayNightNotifications.collectUIState(
            state = {},
            onSuccess = {
                setTheme(R.style.Theme_TestApp)
            },
            onError = {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        )

    }

    override fun onResume() {
        super.onResume()
        App.INSTANCE.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        App.INSTANCE.navigatorHolder.removeNavigator()
        super.onPause()
    }

}