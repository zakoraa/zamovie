package com.raflis.tv_series.data.source.local

import com.raflis.tv_series.data.source.local.entity.TvSeriesEntity
import com.raflis.tv_series.data.source.local.room.TvSeriesDao
import kotlinx.coroutines.flow.Flow

class TvSeriesLocalDataSourceImpl(private val dao: TvSeriesDao) : TvSeriesLocalDataSource {
    override fun getAllTvSeries(): Flow<List<TvSeriesEntity>> = dao.getAllTvSeries()

    override fun getTvSeriesById(id: Int): Flow<TvSeriesEntity> = dao.getTvSeriesById(id)

    override suspend fun insertTvSeries(movieList: List<TvSeriesEntity>) = dao.insertTvSeries(movieList)

    override suspend fun updateTvSeries(movie: TvSeriesEntity) = dao.updateTvSeries(movie)
}