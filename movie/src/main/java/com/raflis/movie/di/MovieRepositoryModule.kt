package com.raflis.movie.di

import com.raflis.movie.data.repository.MovieRepositoryImpl
import com.raflis.movie.data.source.local.MovieLocalDataSource
import com.raflis.movie.data.source.remote.MovieRemoteDataSource
import com.raflis.movie.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module(includes = [MovieNetworkModule::class, MovieDatabaseModule::class])
@InstallIn(SingletonComponent::class)
class MovieRepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        remote: MovieRemoteDataSource,
        local: MovieLocalDataSource,
    ): MovieRepository =
        MovieRepositoryImpl(remote, local)

}