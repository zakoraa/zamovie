package com.raflis.zamovie.favorite_movie.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FavoriteMovie(
    val originalLanguage: String?,
    val title: String?,
    val genreIds: List<Int?>?,
    val posterPath: String?,
    val releaseDate: String?,
    val popularity: Double?,
    val voteAverage: Double?,
    val id: Int?,
) : Parcelable