package com.jeferson.chiper.android.moviedb.data.source

import com.jeferson.chiper.android.moviedb.domain.MovieDomain

interface LocalPopularMoviesDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun getMoviesLocalDB(): List<MovieDomain>
    suspend fun savePopularMovies(movies: List<MovieDomain>)
}