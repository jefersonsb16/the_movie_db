package com.jeferson.chiper.android.moviedb.framework.server

import com.jeferson.chiper.android.moviedb.domain.MovieDomain

fun MovieServerResult.toMovieDomainList(): List<MovieDomain> = results.map {
    it.run {
        MovieDomain(
            adult,
            backdropPath,
            genreIds,
            id,
            originalLanguage,
            originalTitle,
            overview,
            popularity,
            posterPath,
            releaseDate,
            title,
            video,
            voteAverage,
            voteCount
        )
    }
}