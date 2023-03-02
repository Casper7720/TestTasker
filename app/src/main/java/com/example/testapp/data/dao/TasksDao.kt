package com.example.testapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testapp.data.entity.TaskEntity

@Dao
interface TasksDao {
    @Query("SELECT * FROM taskEntity")
    fun findAll(): List<TaskEntity>

    @Query("DELETE FROM taskEntity WHERE id = :id")
    fun deleteById(id: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(task: TaskEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(task: List<TaskEntity>)
}