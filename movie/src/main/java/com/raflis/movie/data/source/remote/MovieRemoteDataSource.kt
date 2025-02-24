package com.raflis.movie.data.source.remote

import com.raflis.core.data.source.remote.network.ApiResponse
import com.raflis.movie.data.source.remote.response.MovieResponse
import com.raflis.movie.domain.model.MovieType
import com.raflis.movie.domain.model.GetMovieByIdParams
import kotlinx.coroutines.flow.Flow

interface MovieRemoteDataSource {
    suspend fun getAllMovies(movieType: MovieType): Flow<ApiResponse<List<MovieResponse>>>
    suspend fun getMovieById(params: GetMovieByIdParams): Flow<ApiResponse<MovieResponse>>
}