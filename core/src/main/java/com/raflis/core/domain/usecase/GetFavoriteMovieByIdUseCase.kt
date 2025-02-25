package com.raflis.core.domain.usecase

import com.raflis.core.domain.model.FavoriteMovie
import com.raflis.core.domain.repository.FavoriteMovieRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteMovieByIdUseCase(private val repository: FavoriteMovieRepository) :
    UseCase<Int, Flow<FavoriteMovie>> {
    override fun invoke(params: Int): Flow<FavoriteMovie> {
        return repository.getFavoriteMovieById(params)
    }
}