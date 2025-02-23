package com.raflis.movie.di

import com.raflis.movie.domain.repository.MovieRepository
import com.raflis.movie.domain.usecase.GetAllMoviesUseCase
import com.raflis.movie.domain.usecase.GetMovieById
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [MovieRepositoryModule::class])
@InstallIn(SingletonComponent::class)
class MovieUseCaseModule {

    @Singleton
    @Provides
    fun provideGetAllMoviesUseCase(
        repository: MovieRepository
    ): GetAllMoviesUseCase =
        GetAllMoviesUseCase(repository)

    @Singleton
    @Provides
    fun provideGetMovieByIdUseCase(
        repository: MovieRepository
    ): GetMovieById =
        GetMovieById(repository)

}