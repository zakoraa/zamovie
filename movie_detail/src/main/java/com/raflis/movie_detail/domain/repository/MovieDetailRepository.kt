package com.raflis.movie_detail.domain.repository

import com.raflis.core.util.ResourceState
import com.raflis.movie_detail.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {
    fun getMovieDetailById(id: Int): Flow<ResourceState<MovieDetail>>
}