package com.jeferson.chiper.android.moviedb.di

import com.jeferson.chiper.android.moviedb.data.source.RemotePopularMoviesDataSource
import com.jeferson.chiper.android.moviedb.framework.server.ApiConstants.API_KEY_VALUE
import com.jeferson.chiper.android.moviedb.framework.server.ApiConstants.BASE_API_URL
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
    fun popularMoviesRequestProvider(
        @Named("baseUrl") baseUrl: String
    ) = PopularMoviesRequest(baseUrl)

    @Provides
    fun remotePopularMoviesDataSourceProvider(
        popularMoviesRequest: PopularMoviesRequest
    ): RemotePopularMoviesDataSource = PopularMoviesRetrofitDataSource(popularMoviesRequest)
}