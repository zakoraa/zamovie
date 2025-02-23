package com.raflis.movie.data.source.remote

import android.util.Log
import com.raflis.core.data.source.remote.network.ApiResponse
import com.raflis.movie.data.source.remote.network.MovieApiService
import com.raflis.movie.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRemoteDataSourceImpl(private val apiService: MovieApiService) : MovieRemoteDataSource {
    override suspend fun getAllMovies(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMovieList()
                val dataArray = response.results?.filterNotNull()

                if (!dataArray.isNullOrEmpty()) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("getAllMovies", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getMovieById(id: Int): Flow<ApiResponse<MovieResponse>> {
        return flow {
            try {
                val response = apiService.getMovieById(id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("getMovieById", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}