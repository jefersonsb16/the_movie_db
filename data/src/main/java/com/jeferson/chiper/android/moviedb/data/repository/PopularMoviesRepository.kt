package com.jeferson.chiper.android.moviedb.data.repository

import com.jeferson.chiper.android.moviedb.data.source.RemotePopularMoviesDataSource
import com.jeferson.chiper.android.moviedb.domain.MovieDomain

class PopularMoviesRepository(
    private val remotePopularMoviesDataSource: RemotePopularMoviesDataSource,
    private val apiKey: String
) {

    suspend fun getPopularMovies(page: Int): List<MovieDomain> =
        remotePopularMoviesDataSource.getPopularMovies(apiKey, page)

}