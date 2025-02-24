package com.raflis.movie.domain.repository

import com.raflis.core.util.Resource
import com.raflis.movie.domain.model.Movie
import com.raflis.movie.domain.model.MovieType
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getAllMovies(movieType: MovieType): Flow<Resource<List<Movie>>>
}