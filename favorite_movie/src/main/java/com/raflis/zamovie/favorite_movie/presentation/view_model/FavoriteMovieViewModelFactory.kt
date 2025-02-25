package com.raflis.zamovie.favorite_movie.presentation.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.raflis.zamovie.favorite_movie.di.Injection

class FavoriteMovieViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(
                Injection.provideGetAllFavoriteMoviesUseCase(context),
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}