package com.raflis.movie_detail.di

import com.raflis.movie_detail.data.repository.MovieDetailRepositoryImpl
import com.raflis.movie_detail.data.source.local.MovieDetailLocalDataSource
import com.raflis.movie_detail.data.source.remote.MovieDetailRemoteDataSource
import com.raflis.movie_detail.domain.repository.MovieDetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object MovieDetailRepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideRepository(
        remote: MovieDetailRemoteDataSource,
        local: MovieDetailLocalDataSource,
    ): MovieDetailRepository =
        MovieDetailRepositoryImpl(remote, local)
}
