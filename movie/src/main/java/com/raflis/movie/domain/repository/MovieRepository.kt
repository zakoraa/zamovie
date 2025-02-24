package com.raflis.movie.domain.repository

import com.raflis.core.util.Resource
import com.raflis.movie.domain.model.Movie
import com.raflis.movie.domain.model.MovieType
import com.raflis.movie.domain.model.GetMovieByIdParams
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getAllMovies(movieType: MovieType): Flow<Resource<List<Movie>>>
    fun getMovieById(params: GetMovieByIdParams): Flow<Resource<Movie>>
}