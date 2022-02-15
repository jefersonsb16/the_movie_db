package com.jeferson.chiper.android.moviedb.data.repository

import com.jeferson.chiper.android.moviedb.data.source.LocalPopularMoviesDataSource
import com.jeferson.chiper.android.moviedb.data.source.RemotePopularMoviesDataSource
import com.jeferson.chiper.android.moviedb.domain.MovieDomain

class PopularMoviesRepository(
    private val remotePopularMoviesDataSource: RemotePopularMoviesDataSource,
    private val localPopularMoviesDataSource: LocalPopularMoviesDataSource,
    private val apiKey: String
) {

    suspend fun getRemotePopularMovies(page: Int): List<MovieDomain> {
        if (localPopularMoviesDataSource.isEmpty()) {
            val movies = remotePopularMoviesDataSource.getPopularMovies(apiKey, page)
            localPopularMoviesDataSource.savePopularMovies(movies)
        }
        return remotePopularMoviesDataSource.getPopularMovies(apiKey, page)
    }

    suspend fun getLocalRoomMovies(): List<MovieDomain> =
        localPopularMoviesDataSource.getMoviesLocalDB()
}