package com.jeferson.chiper.android.moviedb.data.source

import com.jeferson.chiper.android.moviedb.domain.MovieDomain

interface RemotePopularMoviesDataSource {
    suspend fun getPopularMovies(apikey: String, page: Int): List<MovieDomain>
}