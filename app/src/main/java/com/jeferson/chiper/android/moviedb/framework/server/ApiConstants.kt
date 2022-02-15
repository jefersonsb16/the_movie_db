package com.jeferson.chiper.android.moviedb.framework.server

object ApiConstants {
    const val BASE_API_URL = "http://api.themoviedb.org/3/"
    const val END_POINT_POPULAR_MOVIES = "movie/popular"
    const val END_POINT_IMAGES_MOVIE = "movie/{movie_id}/images"
    const val BASE_IMAGE_W500_URL = "https://image.tmdb.org/t/p/w500/"
    const val BASE_IMAGE_ORIGINAL_URL = "https://image.tmdb.org/t/p/original/"

    // Queries
    const val API_KEY = "api_key"
    const val API_KEY_VALUE = "25069d001d741282478abbba7023d1a6"

    const val PAGE_KEY = "page"
    const val LANGUAGE_KEY = "language"
    const val LANGUAGE_VALUE_ES = "es"
}