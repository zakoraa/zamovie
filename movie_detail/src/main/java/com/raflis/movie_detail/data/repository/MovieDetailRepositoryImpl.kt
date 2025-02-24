package com.raflis.movie_detail.data.repository

import android.util.Log
import com.raflis.core.data.NetworkBoundResource
import com.raflis.core.data.source.remote.network.ApiResponse
import com.raflis.core.util.Resource
import com.raflis.movie_detail.data.source.local.MovieDetailLocalDataSource
import com.raflis.movie_detail.data.source.remote.MovieDetailRemoteDataSource
import com.raflis.movie_detail.data.source.remote.response.MovieDetailResponse
import com.raflis.movie_detail.domain.model.MovieDetail
import com.raflis.movie_detail.domain.repository.MovieDetailRepository
import com.raflis.movie_detail.util.MovieDetailDataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieDetailRemoteDataSource,
    private val localDataSource: MovieDetailLocalDataSource,
) : MovieDetailRepository {


    override fun getMovieDetailById(id: Int): Flow<Resource<MovieDetail>> =
        object : NetworkBoundResource<MovieDetail, MovieDetailResponse>() {
            override fun loadFromDB(): Flow<MovieDetail> {
                return localDataSource.getMovieDetailById(id).map {
                    MovieDetailDataMapper.mapEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: MovieDetail?): Boolean =
                data == null

            override suspend fun createCall(): Flow<ApiResponse<MovieDetailResponse>> {
                Log.d("FLORAAAAA", "createCall:${id} ")
              return  remoteDataSource.getMovieDetailById(id)

            }

            override suspend fun saveCallResult(data: MovieDetailResponse) {
                val movieEntity = MovieDetailDataMapper.mapResponseToEntity(data)
                localDataSource.insertMovieDetail(movieEntity)
            }
        }.asFlow()

}