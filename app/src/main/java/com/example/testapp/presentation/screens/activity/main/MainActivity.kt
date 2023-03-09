package com.example.testapp.presentation.screens.activity.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testapp.R
import com.example.testapp.databinding.ActivityMainBinding
import com.example.testapp.databinding.FragmentChapterBinding
import com.example.testapp.presentation.App
import com.example.testapp.presentation.models.LoadingState
import com.example.testapp.presentation.screens.activity.base.BaseActivity
import com.example.testapp.presentation.screens.fragments.chapter.ChapterFragment
import com.example.testapp.presentation.screens.fragments.chapter.ChapterViewModel
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

        viewModel.addDefaultData()

        val chapter = ChapterFragment()
        supportFragmentManager.beginTransaction().apply {
            add(binding.fragmentContainer.id, chapter)
            commit()
        }


    }

    override fun setupListeners() {
        super.setupListeners()
    }

    override fun setupRequests() {
        super.setupRequests()
    }

    override fun setupSubscribers() {
        super.setupSubscribers()

        viewModel.defaultData.collectUIState(
            state = {},
            onError = {
                Toast.makeText(baseContext, it, Toast.LENGTH_LONG).show()
            },
            onSuccess = {
                setTheme(R.style.Theme_TestApp)
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