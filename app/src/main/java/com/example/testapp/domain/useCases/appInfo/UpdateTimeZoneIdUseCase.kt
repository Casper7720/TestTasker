package com.example.testapp.domain.useCases.appInfo

import arrow.core.Either
import com.example.testapp.data.repositories.appInfoRepository.AppInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateTimeZoneIdUseCase @Inject constructor(
    private val appInfoRepository: AppInfoRepository
) {
    operator fun invoke(timeZoneId: String): Flow<Either<String, Boolean>> =
        appInfoRepository.updateTimeZoneId(timeZoneId)
}