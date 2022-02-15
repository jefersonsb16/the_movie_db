package com.jeferson.chiper.android.moviedb.data.repository

import com.jeferson.chiper.android.moviedb.data.source.RemoteImagesMovieByIdDataSource
import com.jeferson.chiper.android.moviedb.domain.ImageMovieDomain

class ImagesMovieByIdRepository(
    private val remoteImagesMovieByIdDataSource: RemoteImagesMovieByIdDataSource,
    private val apiKey: String
) {

    suspend fun getImagesMovieById(movieId: Int): List<ImageMovieDomain> =
        remoteImagesMovieByIdDataSource.getImagesMovieById(movieId, apiKey)
}