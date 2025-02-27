package com.raflis.movie.domain.repository

import com.raflis.core.util.ResourceState
import com.raflis.movie.domain.model.Movie
import com.raflis.movie.domain.model.MovieType
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getAllMovies(movieType: MovieType): Flow<ResourceState<List<Movie>>>
}