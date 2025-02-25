package com.raflis.core.data.source.local

import com.raflis.core.data.source.local.entity.FavoriteMovieEntity
import com.raflis.core.data.source.local.room.FavoriteMovieDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

class FavoriteMovieLocalDataSourceImpl(private val dao: FavoriteMovieDao) :
    FavoriteMovieLocalDataSource {
    override fun getAllFavoriteMovies(): Flow<List<FavoriteMovieEntity>> {
        return dao.getAllFavoriteMovies()
    }

    override fun getFavoriteMovieById(id: Int): Flow<FavoriteMovieEntity> {
        return dao.getFavoriteMovieById(id)
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