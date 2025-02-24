package com.raflis.zamovie.favorite_movie.data.source.local

import com.raflis.zamovie.favorite_movie.data.source.local.entity.FavoriteMovieEntity
import com.raflis.zamovie.favorite_movie.data.source.local.room.FavoriteMovieDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class FavoriteMovieLocalDataSourceImpl @Inject constructor(private val dao: FavoriteMovieDao) :
    FavoriteMovieLocalDataSource {
    override fun getAllFavoriteMovies(): Flow<List<FavoriteMovieEntity>> {
        return dao.getAllFavoriteMovies()
    }

    override suspend fun toggleFavoriteMovie(favoriteMovieEntity: FavoriteMovieEntity) {
        val existingMovie = dao.getFavoriteMovieById(favoriteMovieEntity.id).firstOrNull()

        if (existingMovie != null) {
            dao.deleteFavoriteMovie(existingMovie)
        } else {
            dao.insertFavoriteMovie(favoriteMovieEntity)
        }
    }
}