package com.example.testapp.domain.useCases.appInfo

import arrow.core.Either
import com.example.testapp.data.models.MainScreenType
import com.example.testapp.data.repositories.appInfoRepository.AppInfoRepository
import com.example.testapp.data.repositories.notificationRepository.NotificationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateMainScreenTypeUseCase @Inject constructor(
    private val appInfoRepository: AppInfoRepository
) {
    operator fun invoke(mainScreenType: Int): Flow<Either<String, Boolean>> =
        appInfoRepository.updateMainScreenType(mainScreenType)
}