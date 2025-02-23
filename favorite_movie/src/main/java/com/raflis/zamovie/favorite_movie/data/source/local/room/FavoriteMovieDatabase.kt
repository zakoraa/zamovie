package com.raflis.zamovie.favorite_movie.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raflis.zamovie.favorite_movie.data.source.local.entity.FavoriteMovieEntity

@Database(entities = [FavoriteMovieEntity::class], version = 1, exportSchema = false)
abstract class FavoriteMovieDatabase : RoomDatabase() {
    abstract fun favoriteMovieDao(): FavoriteMovieDao
}