package com.raflis.tv_series.util

import com.raflis.tv_series.data.source.local.entity.TvSeriesEntity
import com.raflis.tv_series.data.source.remote.response.TvSeriesResponse
import com.raflis.tv_series.domain.model.TvSeries

object TvSeriesDataMapper {

    // Convert dari List Response API ke List Entity Database
    fun mapResponsesToEntities(input: List<TvSeriesResponse>): List<TvSeriesEntity> {
        return input.map { mapResponseToEntity(it) }
    }

    // Convert dari Remote API Response ke Database Entity
    fun mapResponseToEntity(input: TvSeriesResponse): TvSeriesEntity {
        return TvSeriesEntity(
            id = input.id ?: 0,
            overview = input.overview ?: "",
            originalLanguage = input.originalLanguage ?: "",
            originalName = input.originalName ?: "",
            name = input.name ?: "",
            genreIds = input.genreIds?.filterNotNull() ?: emptyList(),
            originCountry = input.originCountry?.filterNotNull() ?: emptyList(),
            posterPath = input.posterPath ?: "",
            backdropPath = input.backdropPath ?: "",
            firstAirDate = input.firstAirDate ?: "",
            popularity = input.popularity ?: 0.0,
            voteAverage = input.voteAverage ?: 0.0
        )
    }

    // Convert dari List Entity (Database) ke List Domain Model
    fun mapEntitiesToDomain(input: List<TvSeriesEntity>): List<TvSeries> {
        return input.map { mapEntityToDomain(it) }
    }

    // Convert dari Entity (Database) ke Domain Model (SINGLE OBJECT)
    fun mapEntityToDomain(input: TvSeriesEntity): TvSeries {
        return TvSeries(
            id = input.id,
            overview = input.overview,
            originalLanguage = input.originalLanguage,
            originalName = input.originalName,
            name = input.name,
            genreIds = input.genreIds,
            originCountry = input.originCountry,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath,
            firstAirDate = input.firstAirDate,
            popularity = input.popularity,
            voteAverage = input.voteAverage
        )
    }

    // Convert dari Domain Model ke Entity untuk penyimpanan ke Database
    fun mapDomainToEntity(input: TvSeries): TvSeriesEntity {
        return TvSeriesEntity(
            id = input.id ?: 0,
            overview = input.overview ?: "",
            originalLanguage = input.originalLanguage ?: "",
            originalName = input.originalName ?: "",
            name = input.name ?: "",
            genreIds = input.genreIds?.filterNotNull() ?: emptyList(),
            originCountry = input.originCountry?.filterNotNull() ?: emptyList(),
            posterPath = input.posterPath ?: "",
            backdropPath = input.backdropPath ?: "",
            firstAirDate = input.firstAirDate ?: "",
            popularity = input.popularity ?: 0.0,
            voteAverage = input.voteAverage ?: 0.0
        )
    }
}
