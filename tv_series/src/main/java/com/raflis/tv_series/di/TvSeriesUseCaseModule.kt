package com.raflis.tv_series.di

import com.raflis.movie.di.TvSeriesRepositoryModule
import com.raflis.tv_series.domain.repository.TvSeriesRepository
import com.raflis.tv_series.domain.usecase.GetAllTvSeriessUseCase
import com.raflis.tv_series.domain.usecase.GetTvSeriesById
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [TvSeriesRepositoryModule::class])
@InstallIn(SingletonComponent::class)
class TvSeriesUseCaseModule {

    @Singleton
    @Provides
    fun provideGetAllTvSeriesUseCase(
        repository: TvSeriesRepository
    ): GetAllTvSeriesUseCase =
        GetAllTvSeriesUseCase(repository)

    @Singleton
    @Provides
    fun provideGetTvSeriesByIdUseCase(
        repository: TvSeriesRepository
    ): GetTvSeriesById =
        GetTvSeriesById(repository)

}