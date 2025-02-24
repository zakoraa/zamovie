package com.raflis.movie_detail.util

import com.raflis.movie_detail.data.source.local.entity.MovieDetailEntity
import com.raflis.movie_detail.data.source.remote.response.MovieDetailResponse
import com.raflis.movie_detail.domain.model.MovieDetail
import com.raflis.movie_detail.presentation.model.MovieDetailModel
import java.util.Locale

object MovieDetailDataMapper {

    // **1. Convert dari Response API ke Domain Model**
    fun mapResponseToDomain(input: MovieDetailResponse): MovieDetail {
        return MovieDetail(
            id = input.id ?: 0,
            originalTitle = input.originalTitle.orEmpty(),
            title = input.title.orEmpty(),
            overview = input.overview.orEmpty(),
            posterPath = input.posterPath.orEmpty(),
            releaseDate = input.releaseDate.orEmpty(),
            voteAverage = input.voteAverage.toString().toDoubleOrNull() ?: 0.0,
            homepage = input.homepage.orEmpty(),
            status = input.status.orEmpty()
        )
    }

    // **2. Convert dari Domain ke UI Model**
    fun mapDomainToPresentation(input: MovieDetail): MovieDetailModel {
        return MovieDetailModel(
            id = input.id ?: 0,
            originalTitle = input.originalTitle.orEmpty(),
            title = input.title.orEmpty(),
            overview = input.overview.orEmpty(),
            posterPath = input.posterPath.orEmpty(),
            releaseDate = input.releaseDate.orEmpty(),
            voteAverage = String.format(Locale.US, "%.1f", input.voteAverage ?: 0.0).toDouble(),
            homepage = input.homepage.orEmpty(),
            status = input.status.orEmpty()
        )
    }

    // **3. Convert dari UI Model ke Domain**
    fun mapPresentationToDomain(input: MovieDetailModel): MovieDetail {
        return MovieDetail(
            id = input.id,
            originalTitle = input.originalTitle,
            title = input.title,
            overview = input.overview,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            voteAverage = input.voteAverage,
            homepage = input.homepage,
            status = input.status
        )
    }

    // **4. Convert dari Entity ke Domain Model**
    fun mapEntityToDomain(input: MovieDetailEntity): MovieDetail {
        return MovieDetail(
            id = input.id,
            originalTitle = input.originalTitle.orEmpty(),
            title = input.title.orEmpty(),
            overview = input.overview.orEmpty(),
            posterPath = input.posterPath.orEmpty(),
            releaseDate = input.releaseDate.orEmpty(),
            voteAverage = input.voteAverage ?: 0.0,
            homepage = input.homepage.orEmpty(),
            status = input.status.orEmpty()
        )
    }

    // **5. Convert dari Domain ke Entity untuk Local Database**
    fun mapDomainToEntity(input: MovieDetail): MovieDetailEntity {
        return MovieDetailEntity(
            id = input.id ?: 0,
            originalTitle = input.originalTitle.orEmpty(),
            title = input.title.orEmpty(),
            overview = input.overview.orEmpty(),
            posterPath = input.posterPath.orEmpty(),
            releaseDate = input.releaseDate.orEmpty(),
            voteAverage = input.voteAverage ?: 0.0,
            homepage = input.homepage.orEmpty(),
            status = input.status.orEmpty()
        )
    }

    // **6. Convert dari Response API ke Entity (Menyimpan ke Database)**
    fun mapResponseToEntity(input: MovieDetailResponse): MovieDetailEntity {
        return MovieDetailEntity(
            id = input.id ?: 0,
            originalTitle = input.originalTitle.orEmpty(),
            title = input.title.orEmpty(),
            overview = input.overview.orEmpty(),
            posterPath = input.posterPath.orEmpty(),
            releaseDate = input.releaseDate.orEmpty(),
            voteAverage = input.voteAverage.toString().toDoubleOrNull() ?: 0.0,
            homepage = input.homepage.orEmpty(),
            status = input.status.orEmpty()
        )
    }

    // **7. Convert dari Entity ke Response API (Misalnya untuk keperluan Sync)**
    fun mapEntityToResponse(input: MovieDetailEntity): MovieDetailResponse {
        return MovieDetailResponse(
            id = input.id,
            originalTitle = input.originalTitle,
            title = input.title,
            overview = input.overview,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            voteAverage = input.voteAverage,
            homepage = input.homepage,
            status = input.status
        )
    }
}
