package com.example.testapp.presentation.screens.fragments.base

import androidx.fragment.app.Fragment
import com.example.testapp.presentation.App
import com.github.terrakok.cicerone.Router

open class BaseFragment: Fragment() {

    fun getRouter(): Router = App.INSTANCE.router
}