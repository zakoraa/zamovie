package com.raflis.movie_detail.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.raflis.movie_detail.domain.usecase.GetMovieDetailByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailByIdUseCase: GetMovieDetailByIdUseCase
) :
    ViewModel() {
    fun getMovieDetailById(id: Int) = getMovieDetailByIdUseCase.invoke(id).asLiveData()
}