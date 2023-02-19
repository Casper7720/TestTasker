package com.example.testapp.data

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

@ProvidedTypeConverter
class Converters {
    private val dateTokenType = object : TypeToken<Date>() {}.type

    @TypeConverter
    fun stringToDate(value: String?): Date? {
        value ?: return null
        return Gson().fromJson(value, dateTokenType)
    }

    @TypeConverter
    fun dateToString(priceInfo: Date?): String? {
        priceInfo ?: return null
        val gson = Gson()
        return gson.toJson(priceInfo, dateTokenType)
    }

}