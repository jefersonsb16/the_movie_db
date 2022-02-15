package com.jeferson.chiper.android.moviedb.framework.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PopularMovieDao {

    @Query("SELECT * FROM movie")
    fun getAllMoviesLocalDB(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularMovies(movieList: List<MovieEntity>)

    @Query("SELECT COUNT(id) FROM movie")
    fun movieCount(): Int
}