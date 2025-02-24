package com.raflis.movie_detail.data.source.remote

import com.raflis.core.data.source.remote.network.ApiResponse
import com.raflis.movie_detail.data.source.remote.response.MovieDetailResponse
import kotlinx.coroutines.flow.Flow

interface MovieDetailRemoteDataSource {
    suspend fun getMovieDetailById(id: Int): Flow<ApiResponse<MovieDetailResponse>>
}