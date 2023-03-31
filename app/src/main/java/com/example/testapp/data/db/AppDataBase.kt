package com.example.testapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.testapp.data.Converters
import com.example.testapp.data.dao.FavoriteChapterDao
import com.example.testapp.data.dao.NotificationDao
import com.example.testapp.data.dao.TasksDao
import com.example.testapp.data.entity.FavoriteChapterEntity
import com.example.testapp.data.entity.NotificationEntity
import com.example.testapp.data.entity.TaskEntity

@Database(
    entities = [
        FavoriteChapterEntity::class,
        TaskEntity::class,
        NotificationEntity::class
    ], version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val favoriteChapterDao: FavoriteChapterDao
    abstract val tasksDao: TasksDao
    abstract val notificationDao: NotificationDao
}
