package com.raflis.movie.data.source.remote

import com.raflis.core.data.source.remote.network.ApiResponse
import com.raflis.movie.data.source.remote.response.MovieResponse
import com.raflis.movie.domain.model.MovieType
import kotlinx.coroutines.flow.Flow

interface MovieRemoteDataSource {
    suspend fun getAllMovies(movieType: MovieType): Flow<ApiResponse<List<MovieResponse>>>
}