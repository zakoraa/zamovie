package com.raflis.movie.domain.usecase

import com.raflis.core.util.Resource
import com.raflis.core.domain.usecase.UseCase
import com.raflis.movie.domain.model.Movie
import com.raflis.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetAllMoviesUseCase(private val repository: MovieRepository) :
    UseCase<Unit, Flow<Resource<List<Movie>>>> {
    override suspend fun invoke(params: Unit): Flow<Resource<List<Movie>>> {
        return repository.getAllMovies()
    }
}