package com.raflis.movie_detail.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raflis.movie_detail.data.source.local.entity.MovieDetailEntity

@Database(entities = [MovieDetailEntity::class], version = 1, exportSchema = false)
abstract class MovieDetailDatabase : RoomDatabase() {
    abstract fun movieDetailDao(): MovieDetailDao
}