package com.raflis.movie.di

import android.content.Context
import androidx.room.Room
import com.raflis.core.util.SecureDatabaseUtil.getPassphrase
import com.raflis.movie.data.source.local.MovieLocalDataSource
import com.raflis.movie.data.source.local.MovieLocalDataSourceImpl
import com.raflis.movie.data.source.local.room.MovieDao
import com.raflis.movie.data.source.local.room.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase {
        val passphrase: ByteArray = getPassphrase(context)
        val factory = SupportFactory(passphrase)

        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java, "movie.db"
        ).openHelperFactory(factory)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao = database.movieDao()

    @Singleton
    @Provides
    fun provideMovieLocalDataSource(movieDao: MovieDao): MovieLocalDataSource =
        MovieLocalDataSourceImpl(movieDao)
}