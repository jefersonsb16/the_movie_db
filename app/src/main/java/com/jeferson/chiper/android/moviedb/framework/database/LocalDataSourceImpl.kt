package com.jeferson.chiper.android.moviedb.framework.database

import com.jeferson.chiper.android.moviedb.data.source.LocalPopularMoviesDataSource
import com.jeferson.chiper.android.moviedb.domain.MovieDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PopularMoviesRoomDataSource(
    database: TMDBAppDatabase
) : LocalPopularMoviesDataSource {

    // dao instance
    private val popularMovieDao by lazy { database.popularMovieDao() }

    override suspend fun savePopularMovies(movies: List<MovieDomain>) {
        withContext(Dispatchers.IO) { popularMovieDao.insertPopularMovies(movies.map { it.toMovieEntity() }) }
    }

    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) { popularMovieDao.movieCount() <= 0 }

    override suspend fun getMoviesLocalDB(): List<MovieDomain> = withContext(Dispatchers.IO) {
        popularMovieDao.getAllMoviesLocalDB().map { it.toMovieDomain() }
    }
}