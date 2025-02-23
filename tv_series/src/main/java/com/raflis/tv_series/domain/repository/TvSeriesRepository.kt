package com.raflis.tv_series.domain.repository

import com.raflis.core.data.Resource
import com.raflis.tv_series.domain.model.TvSeries
import kotlinx.coroutines.flow.Flow

interface TvSeriesRepository {
    fun getAllTvSeries(): Flow<Resource<List<TvSeries>>>
    fun getTvSeriesById(id: Int): Flow<Resource<TvSeries>>
}