package com.raflis.tv_series.domain.usecase

import com.raflis.core.data.Resource
import com.raflis.core.domain.usecase.UseCase
import com.raflis.tv_series.domain.model.TvSeries
import com.raflis.tv_series.domain.repository.TvSeriesRepository
import kotlinx.coroutines.flow.Flow

class GetAllTvSeriesUseCase(private val repository: TvSeriesRepository) :
    UseCase<Unit, Flow<Resource<List<TvSeries>>>> {
    override suspend fun invoke(params: Unit): Flow<Resource<List<TvSeries>>> {
        return repository.getAllTvSeries()
    }
}