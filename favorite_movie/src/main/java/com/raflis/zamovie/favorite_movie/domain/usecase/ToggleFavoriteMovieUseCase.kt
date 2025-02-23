package com.raflis.zamovie.favorite_movie.domain.usecase

import com.raflis.core.domain.usecase.UseCase
import com.raflis.zamovie.favorite_movie.domain.model.FavoriteMovie
import com.raflis.zamovie.favorite_movie.domain.repository.FavoriteMovieRepository

class ToggleFavoriteMovieUseCase(private val repository: FavoriteMovieRepository) :
    UseCase<FavoriteMovie, Unit> {

    override suspend fun invoke(params: FavoriteMovie) {
        return repository.toggleFavoriteMovie(params)
    }
}