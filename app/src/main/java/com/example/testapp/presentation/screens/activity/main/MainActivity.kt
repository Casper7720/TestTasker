package com.example.testapp.presentation.screens.activity.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.testapp.R
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.testapp.databinding.ActivityMainBinding
import com.example.testapp.presentation.models.LoadingState
import com.example.testapp.presentation.screens.fragments.chapter.ChapterFragment

class MainActivity : AppCompatActivity() {

    private val userViewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

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


}