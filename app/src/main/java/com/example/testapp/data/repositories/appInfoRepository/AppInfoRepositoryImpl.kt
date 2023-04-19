package com.example.testapp.data.repositories.appInfoRepository

import arrow.core.Either
import com.example.testapp.data.dao.AppInfoDao
import com.example.testapp.data.entity.AppInfoEntity
import com.example.testapp.data.models.MainScreenType
import com.example.testapp.data.repositories.BaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppInfoRepositoryImpl @Inject constructor(
    private val appInfoDao: AppInfoDao
) : AppInfoRepository, BaseRepository() {
    override fun getAppInfo(): Flow<Either<String, AppInfoEntity>> = doRequest {
        appInfoDao.getInfo()
    }

    override fun updateMainScreenType(mainScreenType: Int): Flow<Either<String, Boolean>> = doRequest {
        appInfoDao.updateMainScreenType(mainScreenType)
        true
    }

    override fun setAppInfo(appInfoEntity: AppInfoEntity): Flow<Either<String, Boolean>> = doRequest{
        appInfoDao.add(appInfoEntity)
        true
    }

    override fun updateTimeZoneId(timeZoneId: String): Flow<Either<String, Boolean>> = doRequest {
        appInfoDao.updateTimeZoneId(timeZoneId)
        true
    }

    override fun updateNextWeek(nextWeek: Int): Flow<Either<String, Boolean>> = doRequest {
        appInfoDao.updateNextWeek(nextWeek)
        true
    }

    override fun updateStartWeek(startWeek: Int): Flow<Either<String, Boolean>> = doRequest {
        appInfoDao.updateStartWeek(startWeek)
        true
    }

    override fun updateWeekend(weekend: Int): Flow<Either<String, Boolean>> = doRequest {
        appInfoDao.updateWeekend(weekend)
        true
    }

}