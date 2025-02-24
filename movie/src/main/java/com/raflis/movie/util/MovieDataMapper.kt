package com.raflis.movie.util

import com.raflis.movie.data.source.local.entity.MovieEntity
import com.raflis.movie.data.source.remote.response.MovieResponse
import com.raflis.movie.domain.model.Movie
import com.raflis.movie.domain.model.MovieType

object MovieDataMapper {

    fun mapResponsesToEntities(input: List<MovieResponse>, movieType: MovieType): List<MovieEntity> {
        return input.map { mapResponseToEntity(it, movieType) }
    }

    // Convert dari Remote API Response ke Database Entity
    fun mapResponseToEntity(input: MovieResponse, movieType: MovieType): MovieEntity {
        return MovieEntity(
            id = input.id ?: 0,
            overview = input.overview ?: "",
            originalLanguage = input.originalLanguage ?: "",
            originalTitle = input.originalTitle ?: "",
            title = input.title ?: "",
            genreIds = input.genreIds?.filterNotNull() ?: emptyList(),
            posterPath = input.posterPath ?: "",
            backdropPath = input.backdropPath ?: "",
            releaseDate = input.releaseDate ?: "",
            popularity = input.popularity ?: 0.0,
            voteAverage = input.voteAverage ?: 0.0,
            type = movieType.value
        )
    }

    // Convert dari Entity (Database) ke Domain Model
    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> {
        return input.map {
            Movie(
                id = it.id,
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                title = it.title,
                genreIds = it.genreIds,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                voteAverage = it.voteAverage
            )
        }
    }

    // Convert dari Domain Model ke Entity untuk penyimpanan ke Database
    fun mapDomainToEntity(input: Movie, movieType: MovieType): MovieEntity {
        return MovieEntity(
            id = input.id ?: 0,
            overview = input.overview ?: "",
            originalLanguage = input.originalLanguage ?: "",
            originalTitle = input.originalTitle ?: "",
            title = input.title ?: "",
            genreIds = input.genreIds?.filterNotNull(),
            posterPath = input.posterPath ?: "",
            backdropPath = input.backdropPath ?: "",
            releaseDate = input.releaseDate ?: "",
            popularity = input.popularity ?: 0.0,
            voteAverage = input.voteAverage ?: 0.0,
            type = movieType.value
        )
    }

    // Convert dari Entity (Database) ke Domain Model (SINGLE OBJECT)
    fun mapEntityToDomain(input: MovieEntity): Movie {
        return Movie(
            id = input.id,
            overview = input.overview,
            originalLanguage = input.originalLanguage,
            originalTitle = input.originalTitle,
            title = input.title,
            genreIds = input.genreIds,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath,
            releaseDate = input.releaseDate,
            popularity = input.popularity,
            voteAverage = input.voteAverage
        )
    }
}
