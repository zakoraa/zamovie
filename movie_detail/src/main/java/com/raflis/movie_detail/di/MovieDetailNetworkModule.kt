package com.raflis.movie_detail.di

import com.raflis.core.BuildConfig
import com.raflis.movie_detail.data.source.remote.MovieDetailRemoteDataSource
import com.raflis.movie_detail.data.source.remote.MovieDetailRemoteDataSourceImpl
import com.raflis.movie_detail.data.source.remote.network.MovieDetailApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieDetailNetworkModule {

    @Singleton
    @Provides
    fun provideApiService(client: OkHttpClient): MovieDetailApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(MovieDetailApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideMovieDetailRemoteDataSource(apiService: MovieDetailApiService): MovieDetailRemoteDataSource =
        MovieDetailRemoteDataSourceImpl(apiService)
}