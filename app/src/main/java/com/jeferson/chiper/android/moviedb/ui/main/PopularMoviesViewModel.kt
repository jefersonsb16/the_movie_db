package com.jeferson.chiper.android.moviedb.ui.main

import com.jeferson.chiper.android.moviedb.ui.common.ScopedViewModel
import com.jeferson.chiper.android.moviedb.usecases.GetRemoteMoviesUseCase

class PopularMoviesViewModel(
    private val getPopularMoviesUseCase: GetRemoteMoviesUseCase
): ScopedViewModel() {
}