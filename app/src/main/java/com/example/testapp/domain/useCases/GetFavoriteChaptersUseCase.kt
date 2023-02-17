package com.example.testapp.domain.useCases

import arrow.core.Either
import com.example.testapp.data.entity.FavoriteChapterEntity
import com.example.testapp.data.repositories.favoriteChapterRepository.FavoriteChapterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteChaptersUseCase @Inject constructor(
    private val favoriteChapterRepository: FavoriteChapterRepository
) {
    operator fun invoke(): Flow<Either<String, List<FavoriteChapterEntity>>> =
        favoriteChapterRepository.getAll()

}