package com.example.testapp.presentation

import android.app.Application
import android.widget.Toast
import com.github.terrakok.cicerone.Cicerone


class App : Application() {

    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: App
    }
}