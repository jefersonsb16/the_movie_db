package com.jeferson.chiper.android.moviedb.framework.server

import com.jeferson.chiper.android.moviedb.data.source.RemoteImagesMovieByIdDataSource
import com.jeferson.chiper.android.moviedb.data.source.RemotePopularMoviesDataSource
import com.jeferson.chiper.android.moviedb.domain.ImageMovieDomain
import com.jeferson.chiper.android.moviedb.domain.MovieDomain

class PopularMoviesRetrofitDataSource(
    private val moviesRequest: PopularMoviesRequest
) : RemotePopularMoviesDataSource {
    override suspend fun getPopularMovies(apikey: String, page: Int): List<MovieDomain> =
        moviesRequest.getService<PopularMoviesService>()
            .getPopularMovies(apiKey = apikey, page = page)
            .toMovieDomainList()
}

class ImagesMovieByIdRetrofitDataSource(
    private val imagesMovieRequest: ImagesMovieRequest
) : RemoteImagesMovieByIdDataSource {
    override suspend fun getImagesMovieById(movieId: Int, apikey: String): List<ImageMovieDomain> =
        imagesMovieRequest.getService<ImagesMovieService>()
            .getImagesMovieById(movieId, apikey)
            .toImagesDomainList()
}