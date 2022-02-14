package com.jeferson.chiper.android.moviedb.ui.detail_movie

import com.jeferson.chiper.android.moviedb.domain.MovieDomain
import com.jeferson.chiper.android.moviedb.ui.common.ScopedViewModel
import com.jeferson.chiper.android.moviedb.usecases.GetImagesMovieByIdUseCase

class DetailMovieViewModel(
    private val movie: MovieDomain? = null,
    private val getImagesMovieByIdUseCase: GetImagesMovieByIdUseCase
) : ScopedViewModel() {

    init {
        initScope()
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }

}