package com.jeferson.chiper.android.moviedb.data.source

import com.jeferson.chiper.android.moviedb.domain.ImageMovieDomain

interface RemoteImagesMovieByIdDataSource {
    suspend fun getImagesMovieById(movieId: Int, apiKey: String): List<ImageMovieDomain>
}