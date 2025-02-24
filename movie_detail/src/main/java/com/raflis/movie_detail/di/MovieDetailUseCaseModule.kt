package com.raflis.movie_detail.di

import com.raflis.movie_detail.domain.repository.MovieDetailRepository
import com.raflis.movie_detail.domain.usecase.GetMovieDetailByIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module(includes = [MovieDetailRepositoryModule::class])
@InstallIn(ViewModelComponent::class)
object MovieDetailUseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGetMovieDetailByIdUseCase(
        repository: MovieDetailRepository
    ): GetMovieDetailByIdUseCase =
        GetMovieDetailByIdUseCase(repository)

}