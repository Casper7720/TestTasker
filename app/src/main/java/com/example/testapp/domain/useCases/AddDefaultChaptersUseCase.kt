package com.example.testapp.domain.useCases

import arrow.core.Either
import com.example.testapp.data.entity.FavoriteChapterEntity
import com.example.testapp.data.repositories.favoriteChapterRepository.FavoriteChapterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddDefaultChaptersUseCase @Inject constructor(
    private val favoriteChapterRepository: FavoriteChapterRepository
) {
    operator fun invoke(): Flow<Either<String, Boolean>> =
        favoriteChapterRepository.addDefaultChapters(
            listOf(
                FavoriteChapterEntity(
                    0,
                    "Входящие"
                ),
                FavoriteChapterEntity(
                    1,
                    "Сегодня"
                ),
                FavoriteChapterEntity(
                    2,
                    "Предстоящие"
                ),
                FavoriteChapterEntity(
                    3,
                    "Фильтры и метки"
                ),
            )
        )
}