package com.example.testapp.data.dao

import androidx.room.*
import com.example.testapp.data.entity.NotificationEntity
import java.util.*

@Dao
interface NotificationDao {
    @Query("SELECT * FROM notification")
    fun findAll(): List<NotificationEntity>

    @Query("DELETE FROM notification WHERE id = :id")
    fun deleteById(id: Long)

    @Query("DELETE FROM notification WHERE isDay = :isDay")
    fun deleteByTag(isDay: Boolean)

    @Query("UPDATE notification SET date = :date, dateId = :dateId WHERE isDay = :isDay")
    fun updateDate(isDay: Boolean, date: GregorianCalendar, dateId: Long)

    @Query("UPDATE notification SET isActive = :isActive WHERE isDay = :isDay")
    fun updateActive(isDay: Boolean, isActive: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(notification: List<NotificationEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(notification: NotificationEntity)
}
