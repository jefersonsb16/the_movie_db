package com.jeferson.chiper.android.moviedb.usecases

import com.jeferson.chiper.android.moviedb.data.repository.PopularMoviesRepository
import com.jeferson.chiper.android.moviedb.domain.MovieDomain

class GetRemoteMoviesUseCase(
    private val popularMoviesRepository: PopularMoviesRepository
) {
    suspend fun invoke(page: Int): List<MovieDomain> =
        popularMoviesRepository.getPopularMovies(page)
}