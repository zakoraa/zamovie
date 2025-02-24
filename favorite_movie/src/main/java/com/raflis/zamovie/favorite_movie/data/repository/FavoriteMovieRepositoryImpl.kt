package com.raflis.zamovie.favorite_movie.data.repository

import com.raflis.zamovie.favorite_movie.data.source.local.FavoriteMovieLocalDataSource
import com.raflis.zamovie.favorite_movie.domain.model.FavoriteMovie
import com.raflis.zamovie.favorite_movie.domain.repository.FavoriteMovieRepository
import com.raflis.zamovie.favorite_movie.util.FavoriteMovieDataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteMovieRepositoryImpl @Inject constructor(
    private val localDataSource: FavoriteMovieLocalDataSource,
) : FavoriteMovieRepository {
    override fun getAllFavoriteMovies(): Flow<List<FavoriteMovie>> {
        return localDataSource.getAllFavoriteMovies().map {
            FavoriteMovieDataMapper.mapEntitiesToDomain(it)
        }
    }

    override suspend fun toggleFavoriteMovie(favoriteMovie: FavoriteMovie) {
        val favoriteMovieEntity = FavoriteMovieDataMapper.mapDomainToEntity(favoriteMovie)
        localDataSource.toggleFavoriteMovie(favoriteMovieEntity)
    }
}