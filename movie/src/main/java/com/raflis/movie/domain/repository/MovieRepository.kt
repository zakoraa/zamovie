package com.raflis.movie.domain.repository

import com.raflis.core.util.Resource
import com.raflis.movie.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getAllMovies(): Flow<Resource<List<Movie>>>
    fun getMovieById(id: Int): Flow<Resource<Movie>>
}