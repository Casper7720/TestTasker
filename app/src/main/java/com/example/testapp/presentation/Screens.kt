package com.example.testapp.presentation

import com.example.testapp.presentation.screens.fragments.chapter.ChapterFragment
import com.example.testapp.presentation.screens.fragments.task.TaskFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun chapter() = FragmentScreen { ChapterFragment() }
    fun task() = FragmentScreen { TaskFragment() }
}