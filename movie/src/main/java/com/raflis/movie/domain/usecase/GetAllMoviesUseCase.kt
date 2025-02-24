package com.raflis.movie.domain.usecase

import com.raflis.core.domain.usecase.UseCase
import com.raflis.core.util.Resource
import com.raflis.movie.domain.model.Movie
import com.raflis.movie.domain.model.MovieType
import com.raflis.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMoviesUseCase @Inject constructor(private val repository: MovieRepository) :
    UseCase<MovieType, Flow<Resource<List<Movie>>>> {
    override fun invoke(params: MovieType): Flow<Resource<List<Movie>>> {
        return repository.getAllMovies(params)
    }
}