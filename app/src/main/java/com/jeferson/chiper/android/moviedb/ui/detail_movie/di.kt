package com.jeferson.chiper.android.moviedb.ui.detail_movie

import com.jeferson.chiper.android.moviedb.data.repository.ImagesMovieByIdRepository
import com.jeferson.chiper.android.moviedb.usecases.GetImagesMovieByIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DetailMovieActivityModule {

    @Provides
    @ViewModelScoped
    fun getImagesMovieByIdUseCaseProvider(
        imagesMovieByIdRepository: ImagesMovieByIdRepository
    ) = GetImagesMovieByIdUseCase(imagesMovieByIdRepository)
}