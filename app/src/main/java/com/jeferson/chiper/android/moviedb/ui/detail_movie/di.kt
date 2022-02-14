package com.jeferson.chiper.android.moviedb.ui.detail_movie

import androidx.lifecycle.SavedStateHandle
import com.jeferson.chiper.android.moviedb.data.repository.ImagesMovieByIdRepository
import com.jeferson.chiper.android.moviedb.usecases.GetImagesMovieByIdUseCase
import com.jeferson.chiper.android.moviedb.utils.Constants.MOVIE_ID
import com.jeferson.chiper.android.moviedb.utils.Constants.MOVIE_IMAGE
import com.jeferson.chiper.android.moviedb.utils.Constants.MOVIE_OVERVIEW
import com.jeferson.chiper.android.moviedb.utils.Constants.MOVIE_RELEASE_DATE
import com.jeferson.chiper.android.moviedb.utils.Constants.MOVIE_TITLE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class DetailMovieActivityModule {

    @Provides
    @Named("movieId")
    fun getMovie(stateHandle: SavedStateHandle): Int {
        return stateHandle.get<Int>(MOVIE_ID) ?: 0
    }

    @Provides
    @Named("movieReleaseDate")
    fun getMovieReleaseDate(stateHandle: SavedStateHandle): String {
        return stateHandle.get<String>(MOVIE_RELEASE_DATE) ?: ""
    }

    @Provides
    @Named("movieTitle")
    fun getMovieTitle(stateHandle: SavedStateHandle): String {
        return stateHandle.get<String>(MOVIE_TITLE) ?: ""
    }

    @Provides
    @Named("movieImage")
    fun getMovieImage(stateHandle: SavedStateHandle): String {
        return stateHandle.get<String>(MOVIE_IMAGE) ?: ""
    }

    @Provides
    @Named("movieOverview")
    fun getMovieOverview(stateHandle: SavedStateHandle): String {
        return stateHandle.get<String>(MOVIE_OVERVIEW) ?: ""
    }

    @Provides
    @ViewModelScoped
    fun getImagesMovieByIdUseCaseProvider(
        imagesMovieByIdRepository: ImagesMovieByIdRepository
    ) = GetImagesMovieByIdUseCase(imagesMovieByIdRepository)

    @Provides
    fun detailMovieViewModelProvider(
        @Named("movieId") movieId: Int,
        @Named("movieTitle") movieTitle: String,
        @Named("movieImage") movieImage: String,
        @Named("movieOverview") movieOverview: String,
        @Named("movieOverview") movieReleaseDate: String,
        getImagesMovieByIdUseCase: GetImagesMovieByIdUseCase
    ) = DetailMovieViewModel(
        movieId,
        movieTitle,
        movieImage,
        movieOverview,
        movieReleaseDate,
        getImagesMovieByIdUseCase
    )
}