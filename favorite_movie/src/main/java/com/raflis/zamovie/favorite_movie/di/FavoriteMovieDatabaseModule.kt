package com.raflis.zamovie.favorite_movie.di

import android.content.Context
import androidx.room.Room
import com.raflis.zamovie.favorite_movie.data.source.local.FavoriteMovieLocalDataSource
import com.raflis.zamovie.favorite_movie.data.source.local.FavoriteMovieLocalDataSourceImpl
import com.raflis.zamovie.favorite_movie.data.source.local.room.FavoriteMovieDao
import com.raflis.zamovie.favorite_movie.data.source.local.room.FavoriteMovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoriteMovieDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): FavoriteMovieDatabase =
        Room.databaseBuilder(
            context,
            FavoriteMovieDatabase::class.java, "favorite_movie.db"
        ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideFavoriteMovieDao(database: FavoriteMovieDatabase): FavoriteMovieDao =
        database.favoriteMovieDao()

    @Singleton
    @Provides
    fun provideFavoriteMovieLocalDataSource(favoriteMovieDao: FavoriteMovieDao): FavoriteMovieLocalDataSource =
        FavoriteMovieLocalDataSourceImpl(favoriteMovieDao)
}