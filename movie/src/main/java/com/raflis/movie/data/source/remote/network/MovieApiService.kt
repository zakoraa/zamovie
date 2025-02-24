package com.raflis.movie.data.source.remote.network

import com.raflis.movie.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET

interface MovieApiService {
    @GET("discover/movie")
    suspend fun getMoviesForYou(): ListMovieResponse

    @GET("movie/popular")
    suspend fun getMoviesPopular(): ListMovieResponse

    @GET("movie/top_rated")
    suspend fun getMoviesTopRated(): ListMovieResponse
}