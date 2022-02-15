package com.jeferson.chiper.android.moviedb.ui.main

import com.jeferson.chiper.android.moviedb.domain.MovieDomain

interface OnItemMovieClickListener {
    fun openMovieDetail(movie: MovieDomain)
}