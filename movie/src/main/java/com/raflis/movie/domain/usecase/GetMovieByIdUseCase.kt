package com.raflis.movie.domain.usecase

import com.raflis.core.domain.usecase.UseCase
import com.raflis.core.util.Resource
import com.raflis.movie.domain.model.GetMovieByIdParams
import com.raflis.movie.domain.model.Movie
import com.raflis.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieByIdUseCase @Inject constructor(private val repository: MovieRepository) :
    UseCase<GetMovieByIdParams, Flow<Resource<Movie>>> {
    override fun invoke(params: GetMovieByIdParams): Flow<Resource<Movie>> {
        return repository.getMovieById(params)
    }
}