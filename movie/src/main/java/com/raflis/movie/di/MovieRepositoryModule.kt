package com.raflis.movie.di

import com.raflis.movie.data.repository.MovieRepositoryImpl
import com.raflis.movie.data.source.local.MovieLocalDataSource
import com.raflis.movie.data.source.remote.MovieRemoteDataSource
import com.raflis.movie.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object MovieRepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideRepository(
        remote: MovieRemoteDataSource,
        local: MovieLocalDataSource,
    ): MovieRepository =
        MovieRepositoryImpl(remote, local)
}
