package com.raflis.zamovie.favorite_movie.di

import android.content.Context
import com.raflis.zamovie.favorite_movie.data.repository.FavoriteMovieRepositoryImpl
import com.raflis.zamovie.favorite_movie.data.source.local.FavoriteMovieLocalDataSource
import com.raflis.zamovie.favorite_movie.data.source.local.FavoriteMovieLocalDataSourceImpl
import com.raflis.zamovie.favorite_movie.data.source.local.room.FavoriteMovieDatabase
import com.raflis.zamovie.favorite_movie.domain.repository.FavoriteMovieRepository
import com.raflis.zamovie.favorite_movie.domain.usecase.GetAllFavoriteMoviesUseCase
import com.raflis.zamovie.favorite_movie.domain.usecase.ToggleFavoriteMovieUseCase

object Injection {
    private fun provideFavoriteMovieRepository(context: Context): FavoriteMovieRepository {
        val database = FavoriteMovieDatabase.getInstance(context)
        val dao = database.favoriteMovieDao()
        val localDataSource: FavoriteMovieLocalDataSource = FavoriteMovieLocalDataSourceImpl(dao)

        return FavoriteMovieRepositoryImpl(localDataSource)
    }

    fun provideGetAllFavoriteMoviesUseCase(context: Context): GetAllFavoriteMoviesUseCase {
        val repository = provideFavoriteMovieRepository(context)
        return GetAllFavoriteMoviesUseCase(repository)
    }

    fun provideToggleFavoriteMovieUseCase(context: Context): ToggleFavoriteMovieUseCase {
        val repository = provideFavoriteMovieRepository(context)
        return ToggleFavoriteMovieUseCase(repository)
    }
}
