package com.raflis.movie.data.source.remote.network

import com.raflis.movie.data.source.remote.response.ListMovieResponse
import com.raflis.movie.data.source.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {
    @GET("discover/movie")
    suspend fun getMoviesForYou(): ListMovieResponse

    @GET("movie/popular")
    suspend fun getMoviesPopular(): ListMovieResponse

    @GET("movie/top_rated")
    suspend fun getMoviesTopRated(): ListMovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieById(@Path("movie_id") id: Int): MovieResponse
}