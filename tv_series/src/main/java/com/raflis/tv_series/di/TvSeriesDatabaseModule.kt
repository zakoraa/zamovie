package com.raflis.tv_series.di

import android.content.Context
import androidx.room.Room
import com.raflis.tv_series.data.source.local.room.TvSeriesDao
import com.raflis.tv_series.data.source.local.room.TvSeriesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TvSeriesDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): TvSeriesDatabase = Room.databaseBuilder(
        context,
        TvSeriesDatabase::class.java, "tv_series.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideTvSeriesDao(database: TvSeriesDatabase): TvSeriesDao = database.tvSeriesDao()
}