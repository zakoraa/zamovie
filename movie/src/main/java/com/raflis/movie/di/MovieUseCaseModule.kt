package com.raflis.movie.di

import com.raflis.movie.domain.repository.MovieRepository
import com.raflis.movie.domain.usecase.GetAllMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module(includes = [MovieRepositoryModule::class])
@InstallIn(ViewModelComponent::class)
object MovieUseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGetAllMoviesUseCase(
        repository: MovieRepository
    ): GetAllMoviesUseCase =
        GetAllMoviesUseCase(repository)

}