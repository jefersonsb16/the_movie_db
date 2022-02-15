package com.jeferson.chiper.android.moviedb.test

import com.jeferson.chiper.android.moviedb.data.repository.PopularMoviesRepository
import com.jeferson.chiper.android.moviedb.domain.MovieDomain
import com.jeferson.chiper.android.moviedb.usecases.GetRemoteMoviesUseCase
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetRemoteMoviesUseCaseTest {

    @Mock
    private lateinit var popularMoviesRepository: PopularMoviesRepository

    lateinit var getRemoteMoviesUseCase: GetRemoteMoviesUseCase

    @Before
    fun setUp() {
        getRemoteMoviesUseCase = GetRemoteMoviesUseCase(popularMoviesRepository)
    }

    @Test
    fun `invoke calls popular movies repository`() {
        runBlocking {
            val movies = listOf(mockedMovie.copy(id = 1))
            whenever(popularMoviesRepository.getRemotePopularMovies(1)).thenReturn(movies)

            val result = getRemoteMoviesUseCase.invoke(1)
            Assert.assertEquals(movies, result)
        }
    }
}

val mockedMovie = MovieDomain(
    false,
    "",
    null,
    1,
    "",
    "",
    "Overview",
    0.0,
    "",
    "Date",
    "Title",
    false,
    0.0,
    0
)