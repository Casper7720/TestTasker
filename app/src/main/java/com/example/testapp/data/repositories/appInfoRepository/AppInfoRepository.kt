package com.example.testapp.data.repositories.appInfoRepository

import arrow.core.Either
import com.example.testapp.data.entity.AppInfoEntity
import com.example.testapp.data.models.MainScreenType
import kotlinx.coroutines.flow.Flow

interface AppInfoRepository {
    fun getAppInfo(): Flow<Either<String, AppInfoEntity>>
    fun updateMainScreenType(mainScreenType: Int): Flow<Either<String, Boolean>>
    fun setAppInfo(appInfoEntity: AppInfoEntity): Flow<Either<String, Boolean>>
    fun updateTimeZoneId(timeZoneId: String): Flow<Either<String, Boolean>>
    fun updateStartWeek(startWeek: Int): Flow<Either<String, Boolean>>
    fun updateWeekend(weekend: Int): Flow<Either<String, Boolean>>
    fun updateNextWeek(nextWeek: Int): Flow<Either<String, Boolean>>
}