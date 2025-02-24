package com.raflis.movie_detail.data.source.remote.network

import com.raflis.movie_detail.data.source.remote.response.MovieDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailApiService {
    @GET("movie/{movie_id}")
    suspend fun getMovieDetailById(@Path("movie_id") id: Int): MovieDetailResponse
}