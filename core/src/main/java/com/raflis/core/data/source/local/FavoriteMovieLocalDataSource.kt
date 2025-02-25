package com.raflis.core.data.source.local

import com.raflis.core.data.source.local.entity.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteMovieLocalDataSource {
    fun getAllFavoriteMovies(): Flow<List<FavoriteMovieEntity>>
    fun getFavoriteMovieById(id: Int): Flow<FavoriteMovieEntity>
    suspend fun toggleFavoriteMovie(favoriteMovieEntity: FavoriteMovieEntity)
}