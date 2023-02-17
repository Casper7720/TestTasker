package com.example.testapp.presentation

import android.content.Context
import androidx.room.Room
import com.example.testapp.data.dao.FavoriteChapterDao
import com.example.testapp.data.db.AppDatabase
import com.example.testapp.data.repositories.favoriteChapterRepository.FavoriteChapterRepository
import com.example.testapp.data.repositories.favoriteChapterRepository.FavoriteChapterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideSignInApiService(
        common: Common
    ) = common.api

    @Singleton
    @Provides
    fun provideCommon(): Common = Common

}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @Binds
    abstract fun bindFavoriteChapterRepository(favoriteChapterRepository: FavoriteChapterRepositoryImpl): FavoriteChapterRepository
}

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideFavoriteChapterDao(appDatabase: AppDatabase): FavoriteChapterDao {
        return appDatabase.favoriteChapterDao
    }


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "RssReader"
        ).build()
    }

}