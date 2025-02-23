package com.raflis.movie.data.source.local

import com.raflis.movie.data.source.local.entity.MovieEntity
import com.raflis.movie.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class MovieLocalDataSourceImpl(private val dao: MovieDao) : MovieLocalDataSource {
    override fun getAllMovies(): Flow<List<MovieEntity>> = dao.getAllMovies()

    override fun getMovieById(id: Int): Flow<MovieEntity> = dao.getMovieById(id)

    override suspend fun insertMovies(movieList: List<MovieEntity>) = dao.insertMovies(movieList)

    override suspend fun updateMovie(movie: MovieEntity) = dao.updateMovie(movie)
}