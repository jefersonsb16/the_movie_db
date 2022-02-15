package com.jeferson.chiper.android.moviedb.data.test

import com.jeferson.chiper.android.moviedb.data.repository.ImagesMovieByIdRepository
import com.jeferson.chiper.android.moviedb.data.source.RemoteImagesMovieByIdDataSource
import com.jeferson.chiper.android.moviedb.domain.ImageMovieDomain
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ImagesMovieByIdRepositoryTest {

    @Mock
    lateinit var remoteImagesMovieByIdDataSource: RemoteImagesMovieByIdDataSource

    private val apiKey = "1a2b3c4d"

    private lateinit var imagesMovieByIdRepository: ImagesMovieByIdRepository

    @Before
    fun setUp() {
        imagesMovieByIdRepository =
            ImagesMovieByIdRepository(remoteImagesMovieByIdDataSource, apiKey)
    }

    @Test
    fun `getImagesMovieById should return an expected success list of images`() {
        runBlocking {
            val remoteImagesMovie = listOf(mockedImageMovie.copy())
            whenever(remoteImagesMovieByIdDataSource.getImagesMovieById(1, apiKey)).thenReturn(
                remoteImagesMovie
            )

            val result = imagesMovieByIdRepository.getImagesMovieById(1)
            Assert.assertEquals(remoteImagesMovie, result)
        }
    }
}

val mockedImageMovie = ImageMovieDomain(
    0.0,
    "tmdb/image",
    1,
    "",
    0.0,
    1,
    1
)