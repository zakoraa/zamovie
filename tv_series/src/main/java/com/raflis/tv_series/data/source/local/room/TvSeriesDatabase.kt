package com.raflis.tv_series.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.raflis.tv_series.data.source.local.TvSeriesTypeConverter
import com.raflis.tv_series.data.source.local.entity.TvSeriesEntity

@Database(entities = [TvSeriesEntity::class], version = 1, exportSchema = false)
@TypeConverters(TvSeriesTypeConverter::class)
abstract class TvSeriesDatabase : RoomDatabase() {
    abstract fun tvSeriesDao(): TvSeriesDao
}