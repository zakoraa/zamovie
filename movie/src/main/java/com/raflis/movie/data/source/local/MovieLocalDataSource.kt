package com.raflis.movie.data.source.local

import com.raflis.movie.data.source.local.entity.MovieEntity
import com.raflis.movie.domain.model.MovieType
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    fun getAllMovies(movieType: MovieType): Flow<List<MovieEntity>>
    suspend fun insertMovies(movieList: List<MovieEntity>)

}