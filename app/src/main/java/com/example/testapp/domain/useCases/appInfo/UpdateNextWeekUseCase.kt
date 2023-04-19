package com.example.testapp.domain.useCases.appInfo

import arrow.core.Either
import com.example.testapp.data.repositories.appInfoRepository.AppInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateNextWeekUseCase @Inject constructor(
    private val appInfoRepository: AppInfoRepository
) {
    operator fun invoke(nextWeek: Int): Flow<Either<String, Boolean>> =
        appInfoRepository.updateNextWeek(nextWeek)
}