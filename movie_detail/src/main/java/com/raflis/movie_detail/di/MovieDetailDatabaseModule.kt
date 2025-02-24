package com.raflis.movie_detail.di

import android.content.Context
import androidx.room.Room
import com.raflis.movie_detail.data.source.local.MovieDetailLocalDataSource
import com.raflis.movie_detail.data.source.local.MovieDetailLocalDataSourceImpl
import com.raflis.movie_detail.data.source.local.room.MovieDetailDao
import com.raflis.movie_detail.data.source.local.room.MovieDetailDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieDetailDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MovieDetailDatabase =
        Room.databaseBuilder(
            context,
            MovieDetailDatabase::class.java, "movie_detail.db"
        ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideMovieDetailDao(database: MovieDetailDatabase): MovieDetailDao =
        database.movieDetailDao()

    @Singleton
    @Provides
    fun provideMovieDetailLocalDataSource(movieDao: MovieDetailDao): MovieDetailLocalDataSource =
        MovieDetailLocalDataSourceImpl(movieDao)
}