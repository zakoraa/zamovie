package com.raflis.movie_detail.util

import com.raflis.movie_detail.data.source.local.entity.MovieDetailEntity
import com.raflis.movie_detail.data.source.remote.response.MovieDetailResponse
import com.raflis.movie_detail.domain.model.MovieDetail
import com.raflis.movie_detail.presentation.model.MovieDetailModel
import java.util.Locale

object MovieDetailDataMapper {

    // Convert dari Domain ke UI Model**
    fun mapDomainToPresentation(input: MovieDetail?): MovieDetailModel {
        return MovieDetailModel(
            id = input?.id ?: 0,
            originalTitle = input?.originalTitle.orEmpty(),
            title = input?.title.orEmpty(),
            overview = input?.overview.orEmpty(),
            posterPath = input?.posterPath.orEmpty(),
            releaseDate = input?.releaseDate.orEmpty(),
            voteAverage = String.format(Locale.US, "%.1f", input?.voteAverage ?: 0.0).toDouble(),
            homepage = input?.homepage.orEmpty(),
            status = input?.status.orEmpty()
        )
    }

    // Convert dari Entity ke Domain Model**
    fun mapEntityToDomain(input: MovieDetailEntity?): MovieDetail {
        return MovieDetail(
            id = input?.id,
            originalTitle = input?.originalTitle.orEmpty(),
            title = input?.title.orEmpty(),
            overview = input?.overview.orEmpty(),
            posterPath = input?.posterPath.orEmpty(),
            releaseDate = input?.releaseDate.orEmpty(),
            voteAverage = input?.voteAverage ?: 0.0,
            homepage = input?.homepage.orEmpty(),
            status = input?.status.orEmpty()
        )
    }

    // Convert dari Response API ke Entity (Menyimpan ke Database)**
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

}
