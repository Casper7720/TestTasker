package com.example.testapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testapp.data.entity.GitHubUser

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun findAll(): LiveData<List<GitHubUser>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(users: List<GitHubUser>)
}