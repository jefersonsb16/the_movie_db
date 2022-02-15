package com.jeferson.chiper.android.moviedb.framework.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jeferson.chiper.android.moviedb.framework.database.DBConstants.DATABASE_NAME
import com.jeferson.chiper.android.moviedb.framework.database.DBConstants.DB_VERSION

@Database(
    entities = [MovieEntity::class],
    version = DB_VERSION,
    exportSchema = false
)
abstract class TMDBAppDatabase : RoomDatabase() {

    abstract fun popularMovieDao(): PopularMovieDao

    companion object {

        @Synchronized
        fun getDatabase(context: Context): TMDBAppDatabase = Room.databaseBuilder(
            context.applicationContext,
            TMDBAppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}