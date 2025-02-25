package com.raflis.movie_detail.domain.model

data class MovieDetail(
    val id: Int?,
    val title: String?,
    val overview: String?,
    val originalTitle: String?,
    val posterPath: String?,
    val releaseDate: String?,
    val voteAverage: Double?,
    val homepage: String?,
    val status: String?
)
