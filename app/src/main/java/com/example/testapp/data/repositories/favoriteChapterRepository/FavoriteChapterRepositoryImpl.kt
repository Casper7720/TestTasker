package com.example.testapp.data.repositories.favoriteChapterRepository

import arrow.core.Either
import com.example.testapp.data.dao.FavoriteChapterDao
import com.example.testapp.data.entity.FavoriteChapterEntity
import com.example.testapp.data.repositories.BaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteChapterRepositoryImpl @Inject constructor(
    private val favoriteChapterDao: FavoriteChapterDao
) : BaseRepository(), FavoriteChapterRepository {


    override fun getAll(): Flow<Either<String, List<FavoriteChapterEntity>>> =
        doRequest { favoriteChapterDao.findAll() }

    override fun addFavoriteChapter(chapterEntity: FavoriteChapterEntity): Flow<Either<String, Boolean>> =
        doRequest {
            favoriteChapterDao.add(chapterEntity)
            true
        }

    override fun addDefaultChapters(chaptersEntity: List<FavoriteChapterEntity>): Flow<Either<String, Unit>> =
        doRequest {
            favoriteChapterDao.add(chaptersEntity)
        }
}
