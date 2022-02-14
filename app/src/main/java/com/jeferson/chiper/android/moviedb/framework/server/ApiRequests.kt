package com.jeferson.chiper.android.moviedb.framework.server

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseRequest<T : Any>(
    var baseUrl: String
) {

    private val okHttpClient: OkHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }

    inline fun <reified T : Any> getService(): T =
        buildRetrofit().run {
            create(T::class.java)
        }

    fun buildRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

class PopularMoviesRequest(baseUrl: String) : BaseRequest<PopularMoviesService>(baseUrl)