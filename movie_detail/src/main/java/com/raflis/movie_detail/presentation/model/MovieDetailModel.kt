package com.raflis.movie_detail.presentation.model

data class MovieDetailModel(
    val id: Int,
    val title: String,
    val overview: String,
    val originalTitle: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Double = 0.0,
    val homepage: String,
    val status: String
)
