package com.example.testapp.data

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

@ProvidedTypeConverter
class Converters {
    //Calendar
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

    //GregorianCalendar
    private val gregorianCalendarTolenType = object : TypeToken<GregorianCalendar>() {}.type
    @TypeConverter
    fun stringToGregorianCalendar(value: String?): GregorianCalendar? {
        value ?: return null
        return Gson().fromJson(value, gregorianCalendarTolenType)
    }

    @TypeConverter
    fun gregorianCalendarToString(priceInfo: GregorianCalendar?): String? {
        priceInfo ?: return null
        val gson = Gson()
        return gson.toJson(priceInfo, gregorianCalendarTolenType)
    }

}