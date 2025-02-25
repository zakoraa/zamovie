package com.raflis.core.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.raflis.core.domain.model.FavoriteMovie
import com.raflis.core.domain.usecase.GetAllFavoriteMoviesUseCase
import com.raflis.core.domain.usecase.GetFavoriteMovieByIdUseCase
import com.raflis.core.domain.usecase.ToggleFavoriteMovieUseCase

class FavoriteViewModel(
    getAllFavoriteMoviesUseCase: GetAllFavoriteMoviesUseCase,
    private val toggleFavoriteMovieUseCase: ToggleFavoriteMovieUseCase,
    private val getFavoriteMovieByIdUseCase: GetFavoriteMovieByIdUseCase,
) : ViewModel() {

    val favoriteMovies = getAllFavoriteMoviesUseCase.invoke(Unit).asLiveData()
    suspend fun toggleFavoriteMovie(favoriteMovie: FavoriteMovie) =
        toggleFavoriteMovieUseCase.invoke(favoriteMovie)

    fun isFavoriteMovie(id: Int) = getFavoriteMovieByIdUseCase.invoke(id).asLiveData()
}
