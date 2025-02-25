package com.raflis.core.domain.usecase

import com.raflis.core.domain.model.FavoriteMovie
import com.raflis.core.domain.repository.FavoriteMovieRepository
import kotlinx.coroutines.flow.Flow

class GetAllFavoriteMoviesUseCase(private val repository: FavoriteMovieRepository) :
    UseCase<Unit, Flow<List<FavoriteMovie>>> {
    override fun invoke(params: Unit): Flow<List<FavoriteMovie>> {
        return repository.getAllFavoriteMovies()
    }
}