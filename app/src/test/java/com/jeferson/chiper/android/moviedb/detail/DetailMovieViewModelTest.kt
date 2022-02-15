package com.jeferson.chiper.android.moviedb.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.jeferson.chiper.android.moviedb.domain.ImageMovieDomain
import com.jeferson.chiper.android.moviedb.ui.common.Event
import com.jeferson.chiper.android.moviedb.ui.detail_movie.DetailMovieViewModel
import com.jeferson.chiper.android.moviedb.usecases.GetImagesMovieByIdUseCase
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
class DetailMovieViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val movieId = 1
    private val movieTitle = ""
    private val movieImage = ""
    private val movieOverview = "Overview"
    private val movieReleaseDate = ""

    @Mock
    lateinit var eventsObserve: Observer<Event<DetailMovieViewModel.MovieDetailNavigation>>

    @Mock
    lateinit var getImagesMovieByIdUseCase: GetImagesMovieByIdUseCase

    lateinit var viewModel: DetailMovieViewModel

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(
            movieId,
            movieTitle,
            movieImage,
            movieOverview,
            movieReleaseDate,
            getImagesMovieByIdUseCase,
            Dispatchers.Unconfined
        )
    }

    @Test
    fun `onGetImagesMovieById should return an expected success list of images`() {
        runBlocking {
            val remoteImages = listOf(mockedImageMovie.copy())
            whenever(getImagesMovieByIdUseCase.invoke(1)).thenReturn(remoteImages)

            viewModel.events.observeForever(eventsObserve)
            viewModel.onGetImagesMovieById(1)

            verify(eventsObserve).onChanged(
                ArgumentMatchers.refEq(
                    Event(
                        DetailMovieViewModel.MovieDetailNavigation.ShowImagesMovieList(
                            remoteImages
                        )
                    )
                )
            )
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