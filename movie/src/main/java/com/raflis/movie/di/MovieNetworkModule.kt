package com.raflis.movie.di

import com.raflis.core.BuildConfig
import com.raflis.core.data.source.remote.network.AuthInterceptor
import com.raflis.movie.data.source.remote.MovieRemoteDataSource
import com.raflis.movie.data.source.remote.MovieRemoteDataSourceImpl
import com.raflis.movie.data.source.remote.network.MovieApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieNetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(BuildConfig.API_KEY))
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(client: OkHttpClient): MovieApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(MovieApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(apiService: MovieApiService): MovieRemoteDataSource =
        MovieRemoteDataSourceImpl(apiService)
}