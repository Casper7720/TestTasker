package com.example.testapp.presentation

import androidx.fragment.app.FragmentFactory
import com.example.testapp.presentation.screens.fragments.TaskFragment
import com.example.testapp.presentation.screens.fragments.chapter.ChapterFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    object ChapterScreen : FragmentScreen {
        override fun createFragment(factory: FragmentFactory) = ChapterFragment()
    }

    object TaskScreen : FragmentScreen {
        override fun createFragment(factory: FragmentFactory) = TaskFragment()

    }
}