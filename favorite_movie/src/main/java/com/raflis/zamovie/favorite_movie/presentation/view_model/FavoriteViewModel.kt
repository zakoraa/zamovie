package com.raflis.zamovie.favorite_movie.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.raflis.zamovie.favorite_movie.domain.usecase.GetAllFavoriteMoviesUseCase

class FavoriteViewModel(
    getAllFavoriteMoviesUseCase: GetAllFavoriteMoviesUseCase
) : ViewModel() {

    val favoriteMovies = getAllFavoriteMoviesUseCase.invoke(Unit).asLiveData()
}
