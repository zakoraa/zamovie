package com.raflis.zamovie.favorite_movie.domain.repository

import com.raflis.zamovie.favorite_movie.domain.model.FavoriteMovie
import kotlinx.coroutines.flow.Flow

interface FavoriteMovieRepository {
    fun getAllFavoriteMovies(): Flow<List<FavoriteMovie>>
    suspend fun toggleFavoriteMovie(favoriteMovie: FavoriteMovie)
}
