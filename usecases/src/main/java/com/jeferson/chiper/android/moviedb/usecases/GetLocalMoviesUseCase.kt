package com.jeferson.chiper.android.moviedb.usecases

import com.jeferson.chiper.android.moviedb.data.repository.PopularMoviesRepository
import com.jeferson.chiper.android.moviedb.domain.MovieDomain

class GetLocalMoviesUseCase(
    private val popularMoviesRepository: PopularMoviesRepository
) {
    suspend fun invoke(): List<MovieDomain> =
        popularMoviesRepository.getLocalRoomMovies()
}