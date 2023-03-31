package com.example.testapp.data.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "notification")
data class NotificationEntity(
    @PrimaryKey val id: Long,

    @ColumnInfo(name = "date")
    val date: GregorianCalendar?,

    @ColumnInfo(name = "dateId")
    val dateId: Long,

    @ColumnInfo(name = "isDay")
    val isDay: Boolean,

    @ColumnInfo(name = "isActive")
    var isActive : Boolean
)