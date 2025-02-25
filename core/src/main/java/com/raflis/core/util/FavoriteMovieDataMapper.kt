package com.raflis.core.util

import com.raflis.core.data.source.local.entity.FavoriteMovieEntity
import com.raflis.core.domain.model.FavoriteMovie
import com.raflis.core.presentation.model.FavoriteMovieModel

object FavoriteMovieDataMapper {

    // Convert dari Entity (Database) ke Domain Model
    fun mapEntityToDomain(input: FavoriteMovieEntity?): FavoriteMovie {
        return FavoriteMovie(
            id = input?.id,
            title = input?.title,
            posterPath = input?.posterPath,
            releaseDate = input?.releaseDate,
            voteAverage = input?.voteAverage
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
            title = input.title ?: "",
            posterPath = input.posterPath ?: "",
            releaseDate = input.releaseDate ?: "",
            voteAverage = input.voteAverage ?: 0.0
        )
    }

    // Convert dari List Domain Model ke List Entity
    fun mapDomainsToEntities(input: List<FavoriteMovie>): List<FavoriteMovieEntity> {
        return input.map { mapDomainToEntity(it) }
    }

    // Convert dari Domain Model ke UI Model
    fun mapDomainToPresentation(input: FavoriteMovie): FavoriteMovieModel {
        return FavoriteMovieModel(
            id = input.id,
            title = input.title,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            voteAverage = input.voteAverage
        )
    }

    // Convert dari List Domain Model ke List UI Model
    fun mapDomainsToPresentation(input: List<FavoriteMovie>): List<FavoriteMovieModel> {
        return input.map { mapDomainToPresentation(it) }
    }

    // Convert dari UI Model ke Domain Model
    fun mapPresentationToDomain(input: FavoriteMovieModel): FavoriteMovie {
        return FavoriteMovie(
            id = input.id,
            title = input.title,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            voteAverage = input.voteAverage
        )
    }

    // Convert dari List Presentation Model ke List Domain Model
    fun mapPresentationsToDomains(input: List<FavoriteMovieModel>): List<FavoriteMovie> {
        return input.map { mapPresentationToDomain(it) }
    }
}
