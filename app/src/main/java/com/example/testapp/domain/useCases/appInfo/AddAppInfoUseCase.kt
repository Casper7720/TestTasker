package com.example.testapp.domain.useCases.appInfo

import arrow.core.Either
import com.example.testapp.data.entity.AppInfoEntity
import com.example.testapp.data.repositories.appInfoRepository.AppInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddAppInfoUseCase @Inject constructor(
    private val appInfoRepository: AppInfoRepository
) {
    operator fun invoke(appInfoEntity: AppInfoEntity): Flow<Either<String, Boolean>> =
        appInfoRepository.setAppInfo(appInfoEntity)
}