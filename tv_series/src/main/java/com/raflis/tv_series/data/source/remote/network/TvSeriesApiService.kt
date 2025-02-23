package com.raflis.tv_series.data.source.remote.network

import com.raflis.tv_series.data.source.remote.response.ListTvSeriesResponse
import com.raflis.tv_series.data.source.remote.response.TvSeriesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface TvSeriesApiService {
    @GET("discover/tv")
    suspend fun getTvSeriesList(): ListTvSeriesResponse

    @GET("tv/{series_id}")
    suspend fun getTvSeriesById(@Path("series_id") id: Int): TvSeriesResponse
}