package com.example.testapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoriteChapter")
data class FavoriteChapterEntity(
    @PrimaryKey val id: Long,

    @ColumnInfo(defaultValue = "Входящие")
    val name: String,
    //val image: String
)