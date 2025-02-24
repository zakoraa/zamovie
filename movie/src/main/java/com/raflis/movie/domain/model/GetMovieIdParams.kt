package com.raflis.movie.domain.model

data class GetMovieByIdParams(
    val id: Int,
    val movieType: MovieType
)
