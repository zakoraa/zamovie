package com.raflis.tv_series.data.source.remote

import com.raflis.core.data.source.remote.network.ApiResponse
import com.raflis.tv_series.data.source.remote.response.TvSeriesResponse
import kotlinx.coroutines.flow.Flow

interface TvSeriesRemoteDataSource {
    suspend fun getAllTvSeries(): Flow<ApiResponse<List<TvSeriesResponse>>>
    suspend fun getTvSeriesById(id: Int): Flow<ApiResponse<TvSeriesResponse>>
}