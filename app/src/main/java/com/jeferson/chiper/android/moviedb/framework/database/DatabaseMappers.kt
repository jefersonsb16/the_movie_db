package com.jeferson.chiper.android.moviedb.framework.database

import com.jeferson.chiper.android.moviedb.domain.MovieDomain

fun MovieEntity.toMovieDomain() = MovieDomain(
    adult,
    backdrop_path,
    null,
    id,
    original_language ?: "",
    original_title ?: "",
    overview,
    popularity,
    poster_path,
    release_date,
    title ?: "",
    video,
    vote_average,
    vote_count
)

fun MovieDomain.toMovieEntity() = MovieEntity(
    adult,
    backdrop_path,
    id,
    original_language,
    original_title,
    overview,
    popularity,
    poster_path,
    release_date,
    title,
    video,
    vote_average,
    vote_count
)