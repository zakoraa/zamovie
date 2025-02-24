package com.raflis.movie.di

import android.content.Context
import androidx.room.Room
import com.raflis.movie.data.source.local.MovieLocalDataSource
import com.raflis.movie.data.source.local.MovieLocalDataSourceImpl
import com.raflis.movie.data.source.local.room.MovieDao
import com.raflis.movie.data.source.local.room.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase = Room.databaseBuilder(
        context,
        MovieDatabase::class.java, "movie.db"
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao = database.movieDao()

    @Singleton
    @Provides
    fun provideMovieLocalDataSource(movieDao: MovieDao): MovieLocalDataSource =
        MovieLocalDataSourceImpl(movieDao)
}