package com.raflis.movie.data.source.local

import com.raflis.movie.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    fun getAllMovies(): Flow<List<MovieEntity>>
    fun getMovieById(id: Int): Flow<MovieEntity>
    suspend fun insertMovies(movieList: List<MovieEntity>)
    suspend fun updateMovie(movie: MovieEntity)

}