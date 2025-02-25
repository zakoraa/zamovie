package com.raflis.core.domain.usecase

import com.raflis.core.domain.model.FavoriteMovie
import com.raflis.core.domain.repository.FavoriteMovieRepository


class ToggleFavoriteMovieUseCase (private val repository: FavoriteMovieRepository) {
    suspend fun invoke(params: FavoriteMovie) {
        return repository.toggleFavoriteMovie(params)
    }
}