package com.raflis.movie.data.repository

import com.raflis.core.data.NetworkBoundResource
import com.raflis.core.util.Resource
import com.raflis.core.data.source.remote.network.ApiResponse
import com.raflis.movie.data.source.local.MovieLocalDataSource
import com.raflis.movie.data.source.remote.MovieRemoteDataSource
import com.raflis.movie.data.source.remote.response.MovieResponse
import com.raflis.movie.domain.model.Movie
import com.raflis.movie.domain.repository.MovieRepository
import com.raflis.movie.util.MovieDataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource,
) : MovieRepository {
    override fun getAllMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies().map {
                    MovieDataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val tourismList = MovieDataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovies(tourismList)
            }
        }.asFlow()

    override fun getMovieById(id: Int): Flow<Resource<Movie>> =
        object : NetworkBoundResource<Movie, MovieResponse>() {
            override fun loadFromDB(): Flow<Movie> {
                return localDataSource.getMovieById(id).map {
                    MovieDataMapper.mapEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: Movie?): Boolean =
                data == null

            override suspend fun createCall(): Flow<ApiResponse<MovieResponse>> =
                remoteDataSource.getMovieById(id)

            override suspend fun saveCallResult(data: MovieResponse) {
                val movieEntity = MovieDataMapper.mapResponseToEntity(data)
                localDataSource.updateMovie(movieEntity)
            }
        }.asFlow()

}