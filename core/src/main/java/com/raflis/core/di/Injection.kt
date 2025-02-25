package com.raflis.core.di

import android.content.Context
import com.raflis.core.data.repository.FavoriteMovieRepositoryImpl
import com.raflis.core.data.source.local.FavoriteMovieLocalDataSource
import com.raflis.core.data.source.local.FavoriteMovieLocalDataSourceImpl
import com.raflis.core.data.source.local.room.FavoriteMovieDatabase
import com.raflis.core.domain.repository.FavoriteMovieRepository
import com.raflis.core.domain.usecase.GetAllFavoriteMoviesUseCase
import com.raflis.core.domain.usecase.GetFavoriteMovieByIdUseCase
import com.raflis.core.domain.usecase.ToggleFavoriteMovieUseCase


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

    fun provideGetFavoriteMovieByIdUseCase(context: Context): GetFavoriteMovieByIdUseCase {
        val repository = provideFavoriteMovieRepository(context)
        return GetFavoriteMovieByIdUseCase(repository)
    }
}
