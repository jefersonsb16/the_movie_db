package com.jeferson.chiper.android.moviedb.di

import android.app.Application
import com.jeferson.chiper.android.moviedb.data.source.LocalPopularMoviesDataSource
import com.jeferson.chiper.android.moviedb.data.source.RemoteImagesMovieByIdDataSource
import com.jeferson.chiper.android.moviedb.data.source.RemotePopularMoviesDataSource
import com.jeferson.chiper.android.moviedb.framework.database.PopularMoviesRoomDataSource
import com.jeferson.chiper.android.moviedb.framework.database.TMDBAppDatabase
import com.jeferson.chiper.android.moviedb.framework.server.ApiConstants.API_KEY_VALUE
import com.jeferson.chiper.android.moviedb.framework.server.ApiConstants.BASE_API_URL
import com.jeferson.chiper.android.moviedb.framework.server.ImagesMovieByIdRetrofitDataSource
import com.jeferson.chiper.android.moviedb.framework.server.ImagesMovieRequest
import com.jeferson.chiper.android.moviedb.framework.server.PopularMoviesRequest
import com.jeferson.chiper.android.moviedb.framework.server.PopularMoviesRetrofitDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    @Named("baseUrl")
    fun baseUrlProvider(): String = BASE_API_URL

    @Provides
    @Singleton
    @Named("apiKey")
    fun apiKeyProvider(): String = API_KEY_VALUE

    @Provides
    @Singleton
    fun databaseProvider(app: Application): TMDBAppDatabase = TMDBAppDatabase.getDatabase(app)

    @Provides
    fun localPopularMoviesDataSourceProvider(db: TMDBAppDatabase): LocalPopularMoviesDataSource =
        PopularMoviesRoomDataSource(db)

    @Provides
    fun popularMoviesRequestProvider(
        @Named("baseUrl") baseUrl: String
    ) = PopularMoviesRequest(baseUrl)

    @Provides
    fun remotePopularMoviesDataSourceProvider(
        popularMoviesRequest: PopularMoviesRequest
    ): RemotePopularMoviesDataSource = PopularMoviesRetrofitDataSource(popularMoviesRequest)

    @Provides
    fun imagesMovieByIdRequestProvider(
        @Named("baseUrl") baseUrl: String
    ) = ImagesMovieRequest(baseUrl)

    @Provides
    fun remoteImagesMovieByIdDataSourceProvider(
        imagesMovieRequest: ImagesMovieRequest
    ): RemoteImagesMovieByIdDataSource = ImagesMovieByIdRetrofitDataSource(imagesMovieRequest)
}