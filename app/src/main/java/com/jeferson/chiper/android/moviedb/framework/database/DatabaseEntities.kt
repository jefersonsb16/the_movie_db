package com.jeferson.chiper.android.moviedb.framework.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeferson.chiper.android.moviedb.framework.database.DBConstants.ADULT
import com.jeferson.chiper.android.moviedb.framework.database.DBConstants.BACKDROP_PATH
import com.jeferson.chiper.android.moviedb.framework.database.DBConstants.ID_RESULT
import com.jeferson.chiper.android.moviedb.framework.database.DBConstants.ORIGINAL_LANGUAGE
import com.jeferson.chiper.android.moviedb.framework.database.DBConstants.ORIGINAL_TITLE
import com.jeferson.chiper.android.moviedb.framework.database.DBConstants.OVERVIEW
import com.jeferson.chiper.android.moviedb.framework.database.DBConstants.POPULARITY
import com.jeferson.chiper.android.moviedb.framework.database.DBConstants.POSTER_PATH
import com.jeferson.chiper.android.moviedb.framework.database.DBConstants.RELEASE_DATE
import com.jeferson.chiper.android.moviedb.framework.database.DBConstants.TABLE_MOVIE
import com.jeferson.chiper.android.moviedb.framework.database.DBConstants.TITLE_RESULT
import com.jeferson.chiper.android.moviedb.framework.database.DBConstants.VIDEO_RESULT
import com.jeferson.chiper.android.moviedb.framework.database.DBConstants.VOTE_AVERAGE
import com.jeferson.chiper.android.moviedb.framework.database.DBConstants.VOTE_COUNT

@Entity(tableName = TABLE_MOVIE)
data class MovieEntity(
    @ColumnInfo(name = ADULT) var adult: Boolean,
    @ColumnInfo(name = BACKDROP_PATH) var backdrop_path: String?,
    @PrimaryKey @ColumnInfo(name = ID_RESULT) var id: Int,
    @ColumnInfo(name = ORIGINAL_LANGUAGE) var original_language: String?,
    @ColumnInfo(name = ORIGINAL_TITLE) var original_title: String?,
    @ColumnInfo(name = OVERVIEW) var overview: String?,
    @ColumnInfo(name = POPULARITY) var popularity: Double = 0.0,
    @ColumnInfo(name = POSTER_PATH) var poster_path: String?,
    @ColumnInfo(name = RELEASE_DATE) var release_date: String?,
    @ColumnInfo(name = TITLE_RESULT) var title: String?,
    @ColumnInfo(name = VIDEO_RESULT) var video: Boolean,
    @ColumnInfo(name = VOTE_AVERAGE) var vote_average: Double?,
    @ColumnInfo(name = VOTE_COUNT) var vote_count: Int
)