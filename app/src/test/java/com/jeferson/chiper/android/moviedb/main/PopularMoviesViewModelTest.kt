package com.jeferson.chiper.android.moviedb.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.jeferson.chiper.android.moviedb.domain.MovieDomain
import com.jeferson.chiper.android.moviedb.ui.common.Event
import com.jeferson.chiper.android.moviedb.ui.main.PopularMoviesViewModel
import com.jeferson.chiper.android.moviedb.usecases.GetLocalMoviesUseCase
import com.jeferson.chiper.android.moviedb.usecases.GetRemoteMoviesUseCase
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PopularMoviesViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getPopularMoviesUseCase: GetRemoteMoviesUseCase

    @Mock
    lateinit var getLocalMoviesUseCase: GetLocalMoviesUseCase

    @Mock
    lateinit var eventsObserve: Observer<Event<PopularMoviesViewModel.MovieListNavigation>>

    lateinit var viewModel: PopularMoviesViewModel

    @Before
    fun setUp() {
        viewModel = PopularMoviesViewModel(
            getPopularMoviesUseCase,
            getLocalMoviesUseCase,
            Dispatchers.Unconfined
        )
    }

    @Test
    fun `onGetPopularMovies should return an expected success list of movies`() {
        runBlocking {
            val remoteMovies = listOf(mockedMovie.copy(id = 1))
            whenever(getPopularMoviesUseCase.invoke(1)).thenReturn(remoteMovies)

            viewModel.events.observeForever(eventsObserve)
            viewModel.onGetPopularMovies()

            verify(eventsObserve).onChanged(
                ArgumentMatchers.refEq(
                    Event(
                        PopularMoviesViewModel.MovieListNavigation.ShowMovieList(
                            remoteMovies
                        )
                    )
                )
            )
        }
    }

    @Test
    fun `onGetAllLocalMovies should return an expected success list of movies`() {
        runBlocking {
            val localMovies = listOf(mockedMovie.copy(id = 1))
            whenever(getLocalMoviesUseCase.invoke()).thenReturn(localMovies)

            viewModel.events.observeForever(eventsObserve)
            viewModel.onGetAllLocalMovies()

            verify(eventsObserve).onChanged(
                ArgumentMatchers.refEq(
                    Event(
                        PopularMoviesViewModel.MovieListNavigation.ShowMovieList(
                            localMovies
                        )
                    )
                )
            )
        }
    }

    @Test
    fun `after init view model, onGetPopularMovies is called`() {
        runBlocking {
            val movies = listOf(mockedMovie.copy(id = 1))
            whenever(getPopularMoviesUseCase.invoke(1)).thenReturn(movies)

            viewModel.events.observeForever(eventsObserve)
            viewModel.onGetPopularMovies()

            verify(eventsObserve).onChanged(
                ArgumentMatchers.refEq(
                    Event(
                        PopularMoviesViewModel.MovieListNavigation.ShowMovieList(
                            movies
                        )
                    )
                )
            )
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