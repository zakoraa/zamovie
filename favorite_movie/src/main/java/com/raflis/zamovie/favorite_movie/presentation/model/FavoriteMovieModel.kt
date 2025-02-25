package com.raflis.zamovie.favorite_movie.presentation.model

data class FavoriteMovieModel(
    val originalLanguage: String?,
    val title: String?,
    val posterPath: String?,
    val releaseDate: String?,
    val popularity: Double?,
    val voteAverage: Double?,
    val id: Int?,
)