package com.example.testapp.domain.interactors

import arrow.core.Either
import com.example.testapp.domain.useCases.AddDefaultChaptersUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddDefaultValueInteractor @Inject constructor(
    private val addDefaultChaptersUseCase: @JvmSuppressWildcards AddDefaultChaptersUseCase
) {
    operator fun invoke(): Flow<Either<String, Boolean>> =
        addDefaultChaptersUseCase()
}