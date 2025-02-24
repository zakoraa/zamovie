package com.raflis.movie.presentation

import androidx.lifecycle.ViewModel
import com.raflis.movie.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel(){

}