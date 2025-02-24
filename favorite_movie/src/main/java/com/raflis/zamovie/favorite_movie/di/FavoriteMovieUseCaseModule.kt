package com.raflis.zamovie.favorite_movie.di

import com.raflis.zamovie.favorite_movie.domain.repository.FavoriteMovieRepository
import com.raflis.zamovie.favorite_movie.domain.usecase.GetAllFavoriteMoviesUseCase
import com.raflis.zamovie.favorite_movie.domain.usecase.ToggleFavoriteMovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module(includes = [FavoriteMovieRepositoryModule::class])
@InstallIn(ViewModelComponent::class)
object FavoriteMovieUseCaseModule {

    @Singleton
    @Provides
    fun provideGetAllFavoriteMoviesUseCase(
        repository: FavoriteMovieRepository
    ): GetAllFavoriteMoviesUseCase =
        GetAllFavoriteMoviesUseCase(repository)

    @Singleton
    @Provides
    fun provideToggleFavoriteMovieUseCase(
        repository: FavoriteMovieRepository
    ): ToggleFavoriteMovieUseCase =
        ToggleFavoriteMovieUseCase(repository)

}