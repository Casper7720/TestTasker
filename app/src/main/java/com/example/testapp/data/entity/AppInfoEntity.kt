package com.example.testapp.data.entity

import androidx.annotation.IntDef
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testapp.data.models.MainScreenType

@Entity(tableName = "appInfo")
data class AppInfoEntity(
    @PrimaryKey
    val id: Long,

    @ColumnInfo(name = "mainScreenType", defaultValue = "0")
    val mainScreenType: Int,

    @ColumnInfo(name = "timeZoneId")
    val timeZoneId: String,

    @ColumnInfo(name = "startWeek")
    val startWeek: Int,

    @ColumnInfo(name = "weekend")
    val weekend: Int,

    @ColumnInfo(name = "nextWeek")
    val nextWeek: Int,
)