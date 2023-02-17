package com.example.testapp.data.repositories.favoriteChapterRepository

import arrow.core.Either
import com.example.testapp.data.entity.FavoriteChapterEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteChapterRepository {
    fun getAll() : Flow<Either<String, List<FavoriteChapterEntity>>>
    fun addFavoriteChapter(chapterEntity: FavoriteChapterEntity): Flow<Either<String, Boolean>>
}