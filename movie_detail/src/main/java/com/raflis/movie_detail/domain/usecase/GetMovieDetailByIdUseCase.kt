package com.raflis.movie_detail.domain.usecase

import com.raflis.core.domain.usecase.UseCase
import com.raflis.core.util.Resource
import com.raflis.movie_detail.domain.model.MovieDetail
import com.raflis.movie_detail.domain.repository.MovieDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailByIdUseCase @Inject constructor(private val repository: MovieDetailRepository) :
    UseCase<Int, Flow<Resource<MovieDetail>>> {
    override fun invoke(params: Int): Flow<Resource<MovieDetail>> {
        return repository.getMovieDetailById(params)
    }
}