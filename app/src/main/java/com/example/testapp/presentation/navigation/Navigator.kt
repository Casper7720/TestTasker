package com.example.testapp.presentation.navigation

import com.github.terrakok.cicerone.Command

interface Navigator {
    fun applyCommand(command: Command)
}