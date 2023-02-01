package com.example.testapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testapp.data.dao.UserDao
import com.example.testapp.data.entity.GitHubUser

@Database(entities = [GitHubUser::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}