package com.raflis.movie.data.source.local

import com.raflis.movie.data.source.local.entity.MovieEntity
import com.raflis.movie.data.source.local.room.MovieDao
import com.raflis.movie.domain.model.MovieType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieLocalDataSourceImpl @Inject constructor(private val dao: MovieDao) : MovieLocalDataSource {
    override fun getAllMovies(movieType: MovieType): Flow<List<MovieEntity>> = dao.getAllMovies(movieType.value)

    override fun getMovieById(id: Int): Flow<MovieEntity> = dao.getMovieById(id)

    override suspend fun insertMovies(movieList: List<MovieEntity>) = dao.insertMovies(movieList)

    override suspend fun updateMovie(movie: MovieEntity) = dao.updateMovie(movie)
}