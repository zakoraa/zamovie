package com.raflis.tv_series.data.source.remote

import android.util.Log
import com.raflis.core.data.source.remote.network.ApiResponse
import com.raflis.tv_series.data.source.remote.network.TvSeriesApiService
import com.raflis.tv_series.data.source.remote.response.TvSeriesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TvSeriesRemoteDataSourceImpl(private val apiService: TvSeriesApiService) :
    TvSeriesRemoteDataSource {
    override suspend fun getAllTvSeries(): Flow<ApiResponse<List<TvSeriesResponse>>> {
        return flow {
            try {
                val response = apiService.getTvSeriesList()
                val dataArray = response.results?.filterNotNull()

                if (!dataArray.isNullOrEmpty()) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("getAllTvSeries", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getTvSeriesById(id: Int): Flow<ApiResponse<TvSeriesResponse>> {
        return flow {
            try {
                val response = apiService.getTvSeriesById(id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("getTvSeriesById", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}