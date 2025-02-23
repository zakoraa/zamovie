package com.raflis.tv_series.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.raflis.tv_series.data.source.local.entity.TvSeriesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TvSeriesDao {

    @Query("SELECT * FROM tv_series")
    fun getAllTvSeries(): Flow<List<TvSeriesEntity>>

    @Query("SELECT * FROM tv_series WHERE id = :id")
    fun getTvSeriesById(id: Int): Flow<TvSeriesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvSeries(tourism: List<TvSeriesEntity>)

    @Update
    suspend fun updateTvSeries(tvSeries: TvSeriesEntity)
}