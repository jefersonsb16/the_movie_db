package com.jeferson.chiper.android.moviedb.framework.server

import com.google.gson.annotations.SerializedName

data class MovieServerResult(
    val page: Int,
    val results: List<MovieServer>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)

data class MovieServer(
    val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("genre_ids") val genreIds: List<Int>?,
    val id: Int,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    val overview: String?,
    val popularity: Double,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int
)

data class ImagesMovieResponseServer(
    val backdrops: List<ImageMovieServer>,
    val id: Int,
    val posters: List<PosterServer>
)

data class ImageMovieServer(
    @SerializedName("aspect_ratio") val aspectRatio: Double,
    @SerializedName("file_path") val filePath: String?,
    val height: Int,
    @SerializedName("iso_639_1") val iso639: String?,
    @SerializedName("vote_average") val voteAverage: Double?,
    @SerializedName("vote_count") val voteCount: Int?,
    val width: Int
)

data class PosterServer(
    @SerializedName("aspect_ratio") val aspectRatio: Double,
    @SerializedName("file_path") val filePath: String?,
    val height: Int,
    @SerializedName("iso_639_1") val iso639: String?,
    @SerializedName("vote_average") val voteAverage: Double?,
    @SerializedName("vote_count") val voteCount: Int?,
    val width: Int
)