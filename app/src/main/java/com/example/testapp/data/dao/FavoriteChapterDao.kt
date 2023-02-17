package com.example.testapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testapp.data.entity.FavoriteChapterEntity

@Dao
interface FavoriteChapterDao {
    @Query("SELECT * FROM favoriteChapter")
    fun findAll(): List<FavoriteChapterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(chapter: FavoriteChapterEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(chapters: List<FavoriteChapterEntity>)
}