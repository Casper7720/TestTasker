package com.example.testapp.presentation.screens.activity.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.testapp.R
import com.example.testapp.databinding.ActivityMainBinding
import com.example.testapp.presentation.App
import com.example.testapp.presentation.models.LoadingState
import com.example.testapp.presentation.screens.fragments.chapter.ChapterFragment
import com.github.terrakok.cicerone.androidx.AppNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val userViewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    private val navigator = AppNavigator(this, R.id.fragment_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_TestApp)


        val chapter = ChapterFragment()
        supportFragmentManager.beginTransaction().apply {
            add(binding.fragmentContainer.id, chapter)
            commit()
        }

        setContentView(binding.root)
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