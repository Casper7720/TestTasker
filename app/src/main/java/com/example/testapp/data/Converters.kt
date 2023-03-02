package com.example.testapp.data

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

@ProvidedTypeConverter
class Converters {
    private val calendarTokenType = object : TypeToken<Calendar>() {}.type

    @TypeConverter
    fun stringToCalendar(value: String?): Calendar? {
        value ?: return null
        return Gson().fromJson(value, calendarTokenType)
    }

    @TypeConverter
    fun calendarToString(priceInfo: Calendar?): String? {
        priceInfo ?: return null
        val gson = Gson()
        return gson.toJson(priceInfo, calendarTokenType)
    }

}