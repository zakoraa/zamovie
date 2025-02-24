package com.raflis.zamovie.favorite_movie.util

import com.raflis.zamovie.favorite_movie.domain.model.FavoriteMovie
import com.raflis.zamovie.favorite_movie.data.source.local.entity.FavoriteMovieEntity

object FavoriteMovieDataMapper {

    // Convert dari Entity (Database) ke Domain Model
    fun mapEntityToDomain(input: FavoriteMovieEntity): FavoriteMovie {
        return FavoriteMovie(
            id = input.id,
            originalLanguage = input.originalLanguage,
            title = input.title,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            popularity = input.popularity,
            voteAverage = input.voteAverage
        )
    }

    // Convert dari List Entity (Database) ke List Domain Model
    fun mapEntitiesToDomain(input: List<FavoriteMovieEntity>): List<FavoriteMovie> {
        return input.map { mapEntityToDomain(it) }
    }

    // Convert dari Domain Model ke Entity untuk penyimpanan ke Database
    fun mapDomainToEntity(input: FavoriteMovie): FavoriteMovieEntity {
        return FavoriteMovieEntity(
            id = input.id ?: 0, // Default 0 jika null
            originalLanguage = input.originalLanguage ?: "",
            title = input.title ?: "",
            posterPath = input.posterPath ?: "",
            releaseDate = input.releaseDate ?: "",
            popularity = input.popularity ?: 0.0,
            voteAverage = input.voteAverage ?: 0.0
        )
    }

    // Convert dari List Domain Model ke List Entity
    fun mapDomainsToEntities(input: List<FavoriteMovie>): List<FavoriteMovieEntity> {
        return input.map { mapDomainToEntity(it) }
    }
}
