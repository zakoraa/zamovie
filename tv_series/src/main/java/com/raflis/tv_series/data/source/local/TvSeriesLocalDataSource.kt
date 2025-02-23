package com.raflis.tv_series.data.source.local

import com.raflis.tv_series.data.source.local.entity.TvSeriesEntity
import kotlinx.coroutines.flow.Flow

interface TvSeriesLocalDataSource {
    fun getAllTvSeries(): Flow<List<TvSeriesEntity>>
    fun getTvSeriesById(id: Int): Flow<TvSeriesEntity>
    suspend fun insertTvSeries(movieList: List<TvSeriesEntity>)
    suspend fun updateTvSeries(movie: TvSeriesEntity)
}