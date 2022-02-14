package com.jeferson.chiper.android.moviedb.framework.server

import com.jeferson.chiper.android.moviedb.framework.server.ApiConstants.API_KEY
import com.jeferson.chiper.android.moviedb.framework.server.ApiConstants.API_KEY_VALUE
import com.jeferson.chiper.android.moviedb.framework.server.ApiConstants.END_POINT_POPULAR_MOVIES
import com.jeferson.chiper.android.moviedb.framework.server.ApiConstants.LANGUAGE_KEY
import com.jeferson.chiper.android.moviedb.framework.server.ApiConstants.LANGUAGE_VALUE_ES
import com.jeferson.chiper.android.moviedb.framework.server.ApiConstants.PAGE_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularMoviesService {

    @GET(END_POINT_POPULAR_MOVIES)
    suspend fun getPopularMovies(
        @Query(API_KEY) apiKey: String,
        @Query(PAGE_KEY) page: Int,
        @Query(LANGUAGE_KEY) language: String = LANGUAGE_VALUE_ES
    ): MovieServerResult
}