package com.raflis.tv_series.data.repository

import com.raflis.core.data.NetworkBoundResource
import com.raflis.core.data.Resource
import com.raflis.core.data.source.remote.network.ApiResponse
import com.raflis.tv_series.data.source.local.TvSeriesLocalDataSource
import com.raflis.tv_series.data.source.remote.TvSeriesRemoteDataSource
import com.raflis.tv_series.data.source.remote.response.TvSeriesResponse
import com.raflis.tv_series.domain.model.TvSeries
import com.raflis.tv_series.domain.repository.TvSeriesRepository
import com.raflis.tv_series.util.TvSeriesDataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TvSeriesRepositoryImpl(
    private val remoteDataSource: TvSeriesRemoteDataSource,
    private val localDataSource: TvSeriesLocalDataSource,
) : TvSeriesRepository {
    override fun getAllTvSeries(): Flow<Resource<List<TvSeries>>> =
        object : NetworkBoundResource<List<TvSeries>, List<TvSeriesResponse>>() {
            override fun loadFromDB(): Flow<List<TvSeries>> {
                return localDataSource.getAllTvSeries().map {
                    TvSeriesDataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<TvSeries>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TvSeriesResponse>>> =
                remoteDataSource.getAllTvSeries()

            override suspend fun saveCallResult(data: List<TvSeriesResponse>) {
                val tourismList = TvSeriesDataMapper.mapResponsesToEntities(data)
                localDataSource.insertTvSeries(tourismList)
            }
        }.asFlow()

    override fun getTvSeriesById(id: Int): Flow<Resource<TvSeries>> =
        object : NetworkBoundResource<TvSeries, TvSeriesResponse>() {
            override fun loadFromDB(): Flow<TvSeries> {
                return localDataSource.getTvSeriesById(id).map {
                    TvSeriesDataMapper.mapEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: TvSeries?): Boolean =
                data == null

            override suspend fun createCall(): Flow<ApiResponse<TvSeriesResponse>> =
                remoteDataSource.getTvSeriesById(id)

            override suspend fun saveCallResult(data: TvSeriesResponse) {
                val movieEntity = TvSeriesDataMapper.mapResponseToEntity(data)
                localDataSource.updateTvSeries(movieEntity)
            }
        }.asFlow()

}