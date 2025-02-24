package com.raflis.movie.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.raflis.movie.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie WHERE type = :movieType")
    fun getAllMovies(movieType: String): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getMovieById(id: Int): Flow<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movie: List<MovieEntity>)

    @Update
    suspend fun updateMovie(movie: MovieEntity)
}