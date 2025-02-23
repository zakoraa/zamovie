package com.raflis.tv_series.di

import com.raflis.tv_series.data.repository.TvSeriesRepositoryImpl
import com.raflis.tv_series.data.source.local.TvSeriesLocalDataSource
import com.raflis.tv_series.data.source.remote.TvSeriesRemoteDataSource
import com.raflis.tv_series.domain.repository.TvSeriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module(includes = [TvSeriesNetworkModule::class, TvSeriesDatabaseModule::class])
@InstallIn(SingletonComponent::class)
class TvSeriesRepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        remote: TvSeriesRemoteDataSource,
        local: TvSeriesLocalDataSource,
    ): TvSeriesRepository =
        TvSeriesRepositoryImpl(remote, local)

}