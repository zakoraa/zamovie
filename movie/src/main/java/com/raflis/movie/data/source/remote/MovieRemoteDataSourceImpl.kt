package com.raflis.movie.data.source.remote

import android.util.Log
import com.raflis.core.data.source.remote.network.ApiResponse
import com.raflis.movie.data.source.remote.network.MovieApiService
import com.raflis.movie.data.source.remote.response.MovieResponse
import com.raflis.movie.domain.model.GetMovieByIdParams
import com.raflis.movie.domain.model.MovieType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(private val apiService: MovieApiService) :
    MovieRemoteDataSource {
    override suspend fun getAllMovies(movieType: MovieType): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = when (movieType) {
                    MovieType.FOR_YOU -> apiService.getMoviesForYou()
                    MovieType.TOP_RATED -> apiService.getMoviesTopRated()
                    MovieType.POPULAR -> apiService.getMoviesPopular()
                }

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

    override suspend fun getMovieById(params: GetMovieByIdParams): Flow<ApiResponse<MovieResponse>> {
        return flow {
            try {
                val response = apiService.getMovieById(params.id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("getMovieById", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}