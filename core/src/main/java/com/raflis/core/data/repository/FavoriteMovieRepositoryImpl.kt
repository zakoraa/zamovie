package com.raflis.core.data.repository

import com.raflis.core.data.source.local.FavoriteMovieLocalDataSource
import com.raflis.core.domain.model.FavoriteMovie
import com.raflis.core.domain.repository.FavoriteMovieRepository
import com.raflis.core.util.FavoriteMovieDataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteMovieRepositoryImpl(
    private val localDataSource: FavoriteMovieLocalDataSource,
) : FavoriteMovieRepository {
    override fun getAllFavoriteMovies(): Flow<List<FavoriteMovie>> {
        return localDataSource.getAllFavoriteMovies().map {
            FavoriteMovieDataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getFavoriteMovieById(id: Int): Flow<FavoriteMovie> {
        return localDataSource.getFavoriteMovieById(id).map {
            FavoriteMovieDataMapper.mapEntityToDomain(it)
        }
    }

    override suspend fun toggleFavoriteMovie(favoriteMovie: FavoriteMovie) {
        val favoriteMovieEntity = FavoriteMovieDataMapper.mapDomainToEntity(favoriteMovie)
        localDataSource.toggleFavoriteMovie(favoriteMovieEntity)
    }
}