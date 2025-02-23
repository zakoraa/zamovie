package com.raflis.tv_series.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListTvSeriesResponse(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<TvSeriesResponse?>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
)