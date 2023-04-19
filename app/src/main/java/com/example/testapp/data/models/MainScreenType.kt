package com.example.testapp.data.models

enum class MainScreenType{
    MAIN, TASKS;

    fun getType(): Int =
        when (this) {
            MAIN -> 0
            TASKS -> 1
        }


    companion object{
        fun getMainScreenType(value: Int): MainScreenType =
            when(value){
                0 -> MAIN
                1 -> TASKS
                else -> MAIN
            }
    }
}