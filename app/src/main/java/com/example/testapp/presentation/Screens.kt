package com.example.testapp.presentation

import com.example.testapp.presentation.screens.fragments.chapter.ChapterFragment
import com.example.testapp.presentation.screens.fragments.dayNightNotification.DayNightNotificationFragment
import com.example.testapp.presentation.screens.fragments.notification.NotificationFragment
import com.example.testapp.presentation.screens.fragments.settings.SettingsFragment
import com.example.testapp.presentation.screens.fragments.task.TaskFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun chapter() = FragmentScreen { ChapterFragment() }
    fun task() = FragmentScreen { TaskFragment() }
    fun settings() = FragmentScreen { SettingsFragment() }
    fun notification() = FragmentScreen { NotificationFragment() }
    fun dayNightNotification(isDay: Boolean) = FragmentScreen {
        DayNightNotificationFragment.getNewInstance(isDay)
    }
}