package com.raflis.movie.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.raflis.movie.data.source.local.MovieTypeConverters
import com.raflis.movie.data.source.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
@TypeConverters(MovieTypeConverters::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}