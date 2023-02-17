package com.example.testapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testapp.data.dao.FavoriteChapterDao
import com.example.testapp.data.entity.FavoriteChapterEntity
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Database(entities = [FavoriteChapterEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val favoriteChapterDao: FavoriteChapterDao
}
