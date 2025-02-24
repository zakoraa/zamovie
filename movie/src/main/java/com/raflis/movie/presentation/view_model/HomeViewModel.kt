package com.raflis.movie.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.raflis.movie.domain.model.MovieType
import com.raflis.movie.domain.usecase.GetAllMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllMoviesUseCase: GetAllMoviesUseCase,
) :
    ViewModel() {

    val moviesForYou = getAllMoviesUseCase.invoke(MovieType.FOR_YOU).asLiveData()
    val moviesPopular = getAllMoviesUseCase.invoke(MovieType.POPULAR).asLiveData()
    val moviesTopRated = getAllMoviesUseCase.invoke(MovieType.TOP_RATED).asLiveData()
}