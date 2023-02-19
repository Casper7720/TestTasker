package com.example.testapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "taskEntity")
data class TaskEntity(
    @PrimaryKey val id: Long,

    val title: String,
    val date: Date,


)
