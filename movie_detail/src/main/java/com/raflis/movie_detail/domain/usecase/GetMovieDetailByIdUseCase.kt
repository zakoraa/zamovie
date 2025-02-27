package com.raflis.movie_detail.domain.usecase

import com.raflis.core.domain.usecase.UseCase
import com.raflis.core.util.ResourceState
import com.raflis.movie_detail.domain.model.MovieDetail
import com.raflis.movie_detail.domain.repository.MovieDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailByIdUseCase @Inject constructor(private val repository: MovieDetailRepository) :
    UseCase<Int, Flow<ResourceState<MovieDetail>>> {
    override fun invoke(params: Int): Flow<ResourceState<MovieDetail>> {
        return repository.getMovieDetailById(params)
    }
}