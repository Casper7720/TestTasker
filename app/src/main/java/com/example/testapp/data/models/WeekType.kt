package com.example.testapp.data.models

import android.content.res.Resources
import com.example.testapp.R

enum class WeekType {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    fun getType(): Int = when (this) {
        MONDAY -> 0
        TUESDAY -> 1
        WEDNESDAY -> 2
        THURSDAY -> 3
        FRIDAY -> 4
        SATURDAY -> 5
        SUNDAY -> 6
    }

    fun getName(): Int = when(this){
        MONDAY -> R.string.monday
        TUESDAY -> R.string.tuesday
        WEDNESDAY -> R.string.wednesday
        THURSDAY -> R.string.thursday
        FRIDAY -> R.string.friday
        SATURDAY -> R.string.saturday
        SUNDAY -> R.string.sunday
    }


    companion object {
        fun getWeekType(weekType: Int): WeekType = when (weekType) {
            0 -> MONDAY
            1 -> TUESDAY
            2 -> WEDNESDAY
            3 -> THURSDAY
            4 -> FRIDAY
            5 -> SATURDAY
            else -> SUNDAY
        }
    }

}