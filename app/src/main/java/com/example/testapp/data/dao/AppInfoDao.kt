package com.example.testapp.data.dao

import androidx.room.*
import com.example.testapp.data.entity.AppInfoEntity

@Dao
interface AppInfoDao {

    @Query("SELECT * FROM appInfo")
    fun getInfo(): AppInfoEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(appInfoEntity: AppInfoEntity)

    @Query("UPDATE appInfo SET mainScreenType = :mainScreenType")
    fun updateMainScreenType(mainScreenType: Int)

    @Query("UPDATE appInfo SET timeZoneId = :timeZoneId")
    fun updateTimeZoneId(timeZoneId: String)

    @Query("UPDATE appInfo SET startWeek = :startWeek")
    fun updateStartWeek(startWeek: Int)

    @Query("UPDATE appInfo SET weekend = :weekend")
    fun updateWeekend(weekend: Int)

    @Query("UPDATE appInfo SET nextWeek = :nextWeek")
    fun updateNextWeek(nextWeek: Int)
}