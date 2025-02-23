package com.raflis.zamovie.favorite_movie.data.source.local

import com.raflis.zamovie.favorite_movie.data.source.local.entity.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteMovieLocalDataSource {
    fun getAllFavoriteMovies(): Flow<List<FavoriteMovieEntity>>
    suspend fun toggleFavoriteMovie(favoriteMovieEntity: FavoriteMovieEntity)
}