package com.raflis.tv_series.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvSeries(
    val id: Int?,
    val name: String?,
    val originalName: String?,
    val overview: String?,
    val firstAirDate: String?,
    val originalLanguage: String?,
    val genreIds: List<Int?>?,
    val posterPath: String?,
    val backdropPath: String?,
    val originCountry: List<String?>?,
    val popularity: Double?,
    val voteAverage: Double?
) : Parcelable
