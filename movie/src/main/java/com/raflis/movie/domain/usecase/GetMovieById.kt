package com.raflis.movie.domain.usecase

import com.raflis.core.data.Resource
import com.raflis.core.domain.usecase.UseCase
import com.raflis.movie.domain.model.Movie
import com.raflis.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovieById(private val repository: MovieRepository) :
    UseCase<Int, Flow<Resource<Movie>>> {
    override suspend fun invoke(params: Int): Flow<Resource<Movie>> {
        return repository.getMovieById(params)
    }
}