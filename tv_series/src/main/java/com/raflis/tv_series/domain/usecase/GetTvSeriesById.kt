package com.raflis.tv_series.domain.usecase

import com.raflis.core.data.Resource
import com.raflis.core.domain.usecase.UseCase
import com.raflis.tv_series.domain.model.TvSeries
import com.raflis.tv_series.domain.repository.TvSeriesRepository
import kotlinx.coroutines.flow.Flow

class GetTvSeriesById(private val repository: TvSeriesRepository) :
    UseCase<Int, Flow<Resource<TvSeries>>> {
    override suspend fun invoke(params: Int): Flow<Resource<TvSeries>> {
        return repository.getTvSeriesById(params)
    }
}