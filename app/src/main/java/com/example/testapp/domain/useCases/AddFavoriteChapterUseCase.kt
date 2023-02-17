package com.example.testapp.domain.useCases

import arrow.core.Either
import com.example.testapp.data.entity.FavoriteChapterEntity
import com.example.testapp.data.repositories.favoriteChapterRepository.FavoriteChapterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddFavoriteChapterUseCase @Inject constructor(
    private val favoriteChapterRepository: FavoriteChapterRepository
) {
    operator fun invoke(id: Long, name: String): Flow<Either<String, Boolean>> =
        favoriteChapterRepository.addFavoriteChapter(FavoriteChapterEntity(id, name))


}