package com.example.testapp.domain.useCases.appInfo

import arrow.core.Either
import com.example.testapp.data.repositories.appInfoRepository.AppInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateStartWeekUseCase @Inject constructor(
    private val appInfoRepository: AppInfoRepository
) {
    operator fun invoke(startWeek: Int): Flow<Either<String, Boolean>> =
        appInfoRepository.updateStartWeek(startWeek)
}