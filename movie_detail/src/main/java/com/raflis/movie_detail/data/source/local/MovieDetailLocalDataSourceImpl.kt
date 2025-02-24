package com.raflis.movie_detail.data.source.local

import com.raflis.movie_detail.data.source.local.entity.MovieDetailEntity
import com.raflis.movie_detail.data.source.local.room.MovieDetailDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailLocalDataSourceImpl @Inject constructor(private val dao: MovieDetailDao) : MovieDetailLocalDataSource {
    override fun getMovieDetailById(id: Int): Flow<MovieDetailEntity> = dao.getMovieDetailById(id)
    override suspend fun insertMovieDetail(movieDetail: MovieDetailEntity) = dao.insertMovieDetail(movieDetail)
    override suspend fun updateMovieDetail(movieDetail: MovieDetailEntity) = dao.updateMovieDetail(movieDetail)
}