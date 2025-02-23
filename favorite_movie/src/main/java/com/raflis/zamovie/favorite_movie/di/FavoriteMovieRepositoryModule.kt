package com.raflis.zamovie.favorite_movie.di

import com.raflis.zamovie.favorite_movie.data.repository.FavoriteMovieRepositoryImpl
import com.raflis.zamovie.favorite_movie.data.source.local.FavoriteMovieLocalDataSource
import com.raflis.zamovie.favorite_movie.domain.repository.FavoriteMovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module(includes = [FavoriteMovieDatabaseModule::class])
@InstallIn(SingletonComponent::class)
class FavoriteMovieRepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        local: FavoriteMovieLocalDataSource
    ): FavoriteMovieRepository =
        FavoriteMovieRepositoryImpl(local)

}