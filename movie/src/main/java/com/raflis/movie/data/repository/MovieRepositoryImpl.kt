package com.raflis.movie.data.repository

import com.raflis.core.data.NetworkBoundResource
import com.raflis.core.data.source.remote.network.ApiResponse
import com.raflis.core.util.ResourceState
import com.raflis.movie.data.source.local.MovieLocalDataSource
import com.raflis.movie.data.source.remote.MovieRemoteDataSource
import com.raflis.movie.data.source.remote.response.MovieResponse
import com.raflis.movie.domain.model.Movie
import com.raflis.movie.domain.model.MovieType
import com.raflis.movie.domain.repository.MovieRepository
import com.raflis.movie.util.MovieDataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource,
) : MovieRepository {
    override fun getAllMovies(movieType: MovieType): Flow<ResourceState<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies(movieType).map {
                    MovieDataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies(movieType)

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = MovieDataMapper.mapResponsesToEntities(data, movieType)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()

}