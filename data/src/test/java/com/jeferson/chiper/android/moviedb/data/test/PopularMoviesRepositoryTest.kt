package com.jeferson.chiper.android.moviedb.data.test

import com.jeferson.chiper.android.moviedb.data.repository.PopularMoviesRepository
import com.jeferson.chiper.android.moviedb.data.source.LocalPopularMoviesDataSource
import com.jeferson.chiper.android.moviedb.data.source.RemotePopularMoviesDataSource
import com.jeferson.chiper.android.moviedb.domain.MovieDomain
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PopularMoviesRepositoryTest {

    @Mock
    lateinit var remotePopularMoviesDataSource: RemotePopularMoviesDataSource

    @Mock
    lateinit var localPopularMoviesDataSource: LocalPopularMoviesDataSource

    private lateinit var popularMoviesRepository: PopularMoviesRepository

    private val apiKey = "1a2b3c4d"

    @Before
    fun setUp() {
        popularMoviesRepository =
            PopularMoviesRepository(
                remotePopularMoviesDataSource,
                localPopularMoviesDataSource,
                apiKey
            )
    }

    @Test
    fun `getRemotePopularMovies gets from remote data source`() {
        runBlocking {
            val remoteMovies = listOf(mockedMovie.copy(id = 1))
            whenever(localPopularMoviesDataSource.isEmpty()).thenReturn(false)
            whenever(remotePopularMoviesDataSource.getPopularMovies(apiKey, 1)).thenReturn(remoteMovies)

            val result = popularMoviesRepository.getRemotePopularMovies(1)
            Assert.assertEquals(remoteMovies, result)
        }
    }

    @Test
    fun `getLocalRoomMovies gets from local data source first`() {
        runBlocking {

            val localMovies = listOf(mockedMovie.copy(id = 1))
            whenever(localPopularMoviesDataSource.getMoviesLocalDB()).thenReturn(localMovies)

            val result = popularMoviesRepository.getLocalRoomMovies()

            Assert.assertEquals(localMovies, result)
        }
    }

    @Test
    fun `getRemotePopularMovies saves remote data to local room`() {
        runBlocking {

            val remoteMovies = listOf(mockedMovie.copy(id = 1))
            whenever(localPopularMoviesDataSource.isEmpty()).thenReturn(true)
            whenever(remotePopularMoviesDataSource.getPopularMovies(apiKey, 1)).thenReturn(remoteMovies)

            popularMoviesRepository.getRemotePopularMovies(1)
            verify(localPopularMoviesDataSource).savePopularMovies(remoteMovies)
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