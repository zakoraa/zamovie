package com.raflis.movie.util

import com.raflis.core.util.DateFormatter.formatDate
import com.raflis.movie.data.source.local.entity.MovieEntity
import com.raflis.movie.data.source.remote.response.MovieResponse
import com.raflis.movie.domain.model.Movie
import com.raflis.movie.domain.model.MovieType
import com.raflis.movie.presentation.model.MovieModel
import java.util.Locale

object MovieDataMapper {

    fun mapResponsesToEntities(
        input: List<MovieResponse>,
        movieType: MovieType
    ): List<MovieEntity> {
        return input.map { mapResponseToEntity(it, movieType) }
    }

    // Convert dari Remote API Response ke Database Entity
    private fun mapResponseToEntity(input: MovieResponse, movieType: MovieType): MovieEntity {
        return MovieEntity(
            id = input.id ?: 0,
            overview = input.overview ?: "",
            originalLanguage = input.originalLanguage ?: "",
            originalTitle = input.originalTitle ?: "",
            title = input.title ?: "",
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
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                voteAverage = it.voteAverage
            )
        }
    }

    // Convert dari Domain ke UI Model (SINGLE)
    private fun mapDomainToPresentation(input: Movie): MovieModel {
        return MovieModel(
            id = input.id,
            overview = input.overview,
            originalLanguage = input.originalLanguage,
            originalTitle = input.originalTitle,
            title = input.title,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath,
            releaseDate = formatDate(input.releaseDate),
            popularity = input.popularity,
            voteAverage = String.format(Locale.US, "%.1f", input.voteAverage).toDouble()
        )
    }

    // Convert dari List Domain ke List UI Model
    fun mapDomainListToPresentation(input: List<Movie>): List<MovieModel> {
        return input.map { mapDomainToPresentation(it) }
    }
}
