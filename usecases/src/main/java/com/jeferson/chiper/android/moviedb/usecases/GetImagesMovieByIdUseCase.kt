package com.jeferson.chiper.android.moviedb.usecases

import com.jeferson.chiper.android.moviedb.data.repository.ImagesMovieByIdRepository
import com.jeferson.chiper.android.moviedb.domain.ImageMovieDomain

class GetImagesMovieByIdUseCase(
    private val imagesMovieByIdRepository: ImagesMovieByIdRepository
) {
    suspend fun invoke(movieId: Int): List<ImageMovieDomain> =
        imagesMovieByIdRepository.getImagesMovieById(movieId)
}