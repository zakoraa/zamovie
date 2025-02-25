package com.raflis.core.domain.repository

import com.raflis.core.domain.model.FavoriteMovie
import kotlinx.coroutines.flow.Flow

interface FavoriteMovieRepository {
    fun getAllFavoriteMovies(): Flow<List<FavoriteMovie>>
    fun getFavoriteMovieById(id: Int): Flow<FavoriteMovie>
    suspend fun toggleFavoriteMovie(favoriteMovie: FavoriteMovie)
}
