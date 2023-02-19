package com.example.testapp.presentation

import android.content.Context
import androidx.room.Room
import com.example.testapp.data.Converters
import com.example.testapp.data.dao.FavoriteChapterDao
import com.example.testapp.data.dao.TasksDao
import com.example.testapp.data.db.AppDatabase
import com.example.testapp.data.repositories.favoriteChapterRepository.FavoriteChapterRepository
import com.example.testapp.data.repositories.favoriteChapterRepository.FavoriteChapterRepositoryImpl
import com.example.testapp.data.repositories.tasksRepository.TasksRepository
import com.example.testapp.data.repositories.tasksRepository.TasksRepositoryImpl
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

    @Binds
    abstract fun bindTasksRepository(tasksRepository: TasksRepositoryImpl): TasksRepository
}

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideFavoriteChapterDao(appDatabase: AppDatabase): FavoriteChapterDao =
        appDatabase.favoriteChapterDao


    @Provides
    fun provideTasksDao(appDatabase: AppDatabase): TasksDao = appDatabase.tasksDao

    @Singleton
    @Provides
    fun provideConverter(): Converters = Converters()


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context, converters: Converters): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "RssReader"
        ).addTypeConverter(converters).build()
    }

}