package com.jeferson.chiper.android.moviedb.framework.server

import com.jeferson.chiper.android.moviedb.domain.ImageMovieDomain
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

fun ImagesMovieResponseServer.toImagesDomainList(): List<ImageMovieDomain> = backdrops.map {
    it.run {
        ImageMovieDomain(
            aspectRatio,
            filePath,
            height,
            iso639,
            voteAverage,
            voteCount,
            width
        )
    }
}