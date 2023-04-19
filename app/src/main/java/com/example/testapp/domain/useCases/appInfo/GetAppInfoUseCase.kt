package com.example.testapp.domain.useCases.appInfo

import arrow.core.Either
import com.example.testapp.data.entity.AppInfoEntity
import com.example.testapp.data.entity.NotificationEntity
import com.example.testapp.data.repositories.appInfoRepository.AppInfoRepository
import com.example.testapp.data.repositories.notificationRepository.NotificationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAppInfoUseCase @Inject constructor(
    private val appInfoRepository: AppInfoRepository
) {
    operator fun invoke(): Flow<Either<String, AppInfoEntity>> =
        appInfoRepository.getAppInfo()
}