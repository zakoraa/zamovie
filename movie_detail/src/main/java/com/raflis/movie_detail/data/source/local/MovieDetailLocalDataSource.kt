package com.raflis.movie_detail.data.source.local

import com.raflis.movie_detail.data.source.local.entity.MovieDetailEntity
import kotlinx.coroutines.flow.Flow

interface MovieDetailLocalDataSource {
    fun getMovieDetailById(id: Int): Flow<MovieDetailEntity>
    suspend fun insertMovieDetail(movieDetail: MovieDetailEntity)
    suspend fun updateMovieDetail(movieDetail: MovieDetailEntity)
}