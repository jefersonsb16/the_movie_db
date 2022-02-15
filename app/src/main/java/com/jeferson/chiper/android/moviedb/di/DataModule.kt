package com.jeferson.chiper.android.moviedb.di

import com.jeferson.chiper.android.moviedb.data.repository.ImagesMovieByIdRepository
import com.jeferson.chiper.android.moviedb.data.repository.PopularMoviesRepository
import com.jeferson.chiper.android.moviedb.data.source.LocalPopularMoviesDataSource
import com.jeferson.chiper.android.moviedb.data.source.RemoteImagesMovieByIdDataSource
import com.jeferson.chiper.android.moviedb.data.source.RemotePopularMoviesDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun popularMoviesRepositoryProvider(
        remotePopularMoviesDataSource: RemotePopularMoviesDataSource,
        localPopularMoviesDataSource: LocalPopularMoviesDataSource,
        @Named("apiKey") apiKey: String
    ) = PopularMoviesRepository(remotePopularMoviesDataSource, localPopularMoviesDataSource, apiKey)

    @Provides
    fun imagesMovieByIdRepositoryProvider(
        remoteImagesMovieByIdDataSource: RemoteImagesMovieByIdDataSource,
        @Named("apiKey") apiKey: String
    ) = ImagesMovieByIdRepository(remoteImagesMovieByIdDataSource, apiKey)
}