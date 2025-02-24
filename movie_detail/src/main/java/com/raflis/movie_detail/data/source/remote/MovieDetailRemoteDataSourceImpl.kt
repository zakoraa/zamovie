package com.raflis.movie_detail.data.source.remote

import android.util.Log
import com.raflis.core.data.source.remote.network.ApiResponse
import com.raflis.movie_detail.data.source.remote.network.MovieDetailApiService
import com.raflis.movie_detail.data.source.remote.response.MovieDetailResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieDetailRemoteDataSourceImpl @Inject constructor(private val apiService: MovieDetailApiService) :
    MovieDetailRemoteDataSource {

    override suspend fun getMovieDetailById(id: Int): Flow<ApiResponse<MovieDetailResponse>> {
        return flow {
            try {
                val response = apiService.getMovieDetailById(id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("getMovieDetailById", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}