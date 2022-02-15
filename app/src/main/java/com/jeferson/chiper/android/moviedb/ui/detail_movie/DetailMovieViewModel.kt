package com.jeferson.chiper.android.moviedb.ui.detail_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jeferson.chiper.android.moviedb.domain.ImageMovieDomain
import com.jeferson.chiper.android.moviedb.domain.MovieDomain
import com.jeferson.chiper.android.moviedb.ui.common.Event
import com.jeferson.chiper.android.moviedb.ui.common.ScopedViewModel
import com.jeferson.chiper.android.moviedb.usecases.GetImagesMovieByIdUseCase
import com.jeferson.chiper.android.moviedb.utils.MessageErrorFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val movieId: Int,
    private val movieTitle: String,
    private val movieImage: String,
    private val movieOverview: String,
    private val movieReleaseDate: String,
    private val getImagesMovieByIdUseCase: GetImagesMovieByIdUseCase,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    private val _imagesMovieList: MutableList<ImageMovieDomain> = mutableListOf()

    private val _events = MutableLiveData<Event<MovieDetailNavigation>>()
    val events: LiveData<Event<MovieDetailNavigation>> get() = _events

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _sizeImagesList = MutableLiveData(0)
    val sizeImagesList: LiveData<Int> get() = _sizeImagesList

    private val _movieValues = MutableLiveData<MovieDomain>()
    val movieValues: LiveData<MovieDomain> get() = _movieValues

    init {
        initScope()
    }

    fun initializeMovieLiveData() {
        val movie = MovieDomain(
            false,
            movieImage,
            null,
            movieId,
            "",
            "",
            movieOverview,
            0.0,
            null,
            movieReleaseDate,
            movieTitle,
            false,
            null,
            0
        )
        _movieValues.value = movie
        onGetImagesMovieById(movie.id)
    }

    fun onGetImagesMovieById(id: Int) {
        launch {
            showLoading()
            try {
                val resultImages = getImagesMovieByIdUseCase.invoke(id)
                _sizeImagesList.value = resultImages.size
                _imagesMovieList.clear()
                _imagesMovieList.addAll(resultImages)
                hideLoading()

                _events.value = Event(MovieDetailNavigation.ShowImagesMovieList(_imagesMovieList))
            } catch (e: Exception) {
                hideLoading()
                var errorCode: Int = MessageErrorFactory.GENERIC_ERROR

                if (e is HttpException) {
                    errorCode = e.code()
                }
                _imagesMovieList.clear()
                _sizeImagesList.value = 0
                _events.value = Event(MovieDetailNavigation.ShowImagesMovieError(errorCode))
            }
        }
    }

    // handling progress
    private fun showLoading() {
        _isLoading.value = true
        _events.value = Event(MovieDetailNavigation.ShowImagesMovieLoading)
    }

    private fun hideLoading() {
        _isLoading.value = false
        _events.value = Event(MovieDetailNavigation.HideImagesMovieLoading)
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }

    sealed class MovieDetailNavigation {
        data class ShowImagesMovieError(val error: Int) : MovieDetailNavigation()
        data class ShowImagesMovieList(val imagesMovieList: List<ImageMovieDomain>) : MovieDetailNavigation()
        object HideImagesMovieLoading : MovieDetailNavigation()
        object ShowImagesMovieLoading : MovieDetailNavigation()
        object CloseActivity : MovieDetailNavigation()
    }

}