package com.raflis.movie.presentation.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieModel(
    val overview: String?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val title: String?,
    val posterPath: String?,
    val backdropPath: String?,
    val releaseDate: String?,
    val popularity: Double?,
    val voteAverage: Double?,
    val id: Int?,
) : Parcelable
