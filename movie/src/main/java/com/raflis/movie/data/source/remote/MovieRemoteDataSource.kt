package com.raflis.movie.data.source.remote

import com.raflis.core.data.source.remote.network.ApiResponse
import com.raflis.movie.data.source.remote.response.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieRemoteDataSource {
    suspend fun getAllMovies(): Flow<ApiResponse<List<MovieResponse>>>
    suspend fun getMovieById(id: Int): Flow<ApiResponse<MovieResponse>>
}