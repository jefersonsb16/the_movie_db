package com.jeferson.chiper.android.moviedb.test

import com.jeferson.chiper.android.moviedb.data.repository.ImagesMovieByIdRepository
import com.jeferson.chiper.android.moviedb.domain.ImageMovieDomain
import com.jeferson.chiper.android.moviedb.usecases.GetImagesMovieByIdUseCase
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetImagesMovieByIdUseCaseTest {

    @Mock
    private lateinit var imagesMovieByIdRepository: ImagesMovieByIdRepository

    lateinit var getImagesMovieByIdUseCase: GetImagesMovieByIdUseCase

    @Before
    fun setUp() {
        getImagesMovieByIdUseCase = GetImagesMovieByIdUseCase(imagesMovieByIdRepository)
    }

    @Test
    fun `invoke calls images movie repository`() {
        runBlocking {
            val imagesMovie = listOf(mockedImageMovie.copy())
            whenever(imagesMovieByIdRepository.getImagesMovieById(1)).thenReturn(imagesMovie)

            val result = getImagesMovieByIdUseCase.invoke(1)
            Assert.assertEquals(imagesMovie, result)
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