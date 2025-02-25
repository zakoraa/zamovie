package com.raflis.movie_detail.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raflis.core.util.Resource
import com.raflis.movie_detail.domain.model.MovieDetail
import com.raflis.movie_detail.domain.usecase.GetMovieDetailByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailByIdUseCase: GetMovieDetailByIdUseCase
) : ViewModel() {

    private val _movieDetail = MutableStateFlow<Resource<MovieDetail>>(Resource.Loading())
    val movieDetail: StateFlow<Resource<MovieDetail>> = _movieDetail.asStateFlow()

    fun fetchMovieDetail(id: Int) {
        viewModelScope.launch {
            getMovieDetailByIdUseCase.invoke(id)
                .collect { result ->
                    _movieDetail.value = result
                }
        }
    }
}
