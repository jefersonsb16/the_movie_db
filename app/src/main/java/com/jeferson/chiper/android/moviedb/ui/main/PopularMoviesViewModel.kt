package com.jeferson.chiper.android.moviedb.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jeferson.chiper.android.moviedb.domain.MovieDomain
import com.jeferson.chiper.android.moviedb.ui.common.Event
import com.jeferson.chiper.android.moviedb.ui.common.ScopedViewModel
import com.jeferson.chiper.android.moviedb.usecases.GetRemoteMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetRemoteMoviesUseCase
) : ScopedViewModel() {

    private val _events = MutableLiveData<Event<MovieListNavigation>>()
    val events: LiveData<Event<MovieListNavigation>> get() = _events

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _sizeMoviesList = MutableLiveData(0)
    val sizeMoviesList: LiveData<Int> get() = _sizeMoviesList

    private val _popularMovieList: MutableList<MovieDomain> = mutableListOf()

    private var currentPage = 0
    private var isLastPage = false

    init {
        initScope()
    }

    fun onGetPopularMovies() {
        currentPage += 1

        launch {
            showLoading()
            val resultList = getPopularMoviesUseCase.invoke(currentPage)
            _popularMovieList.addAll(resultList)
            _sizeMoviesList.value = _popularMovieList.size
            hideLoading()

            if (resultList.size < PAGE_SIZE) {
                isLastPage = true
            }

            _events.value = Event(MovieListNavigation.ShowMovieList(_popularMovieList))
        }
    }

    fun onLoadMoreMovies(visibleItemCount: Int, firstVisibleItemPosition: Int, totalItemCount: Int) {
        if (isLoading.value!! || isLastPage || !isInFooter(
                visibleItemCount,
                firstVisibleItemPosition,
                totalItemCount
            )
        ) {
            return
        }

        currentPage += 1
        onGetPopularMovies()
    }

    fun onRetryGetPopularMovies() {
        _popularMovieList.clear()
        currentPage = 0
        isLastPage = false
        onGetPopularMovies()
    }

    private fun isInFooter(
        visibleItemCount: Int,
        firstVisibleItemPosition: Int,
        totalItemCount: Int
    ): Boolean {
        return visibleItemCount + firstVisibleItemPosition >= totalItemCount
                && firstVisibleItemPosition >= 0
                && totalItemCount >= PAGE_SIZE
    }

    private fun showLoading() {
        _isLoading.value = true
        _events.value = Event(MovieListNavigation.ShowLoading)
    }

    private fun hideLoading() {
        _isLoading.value = false
        _events.value = Event(MovieListNavigation.HideLoading)
    }

    // events for handling navigation in view
    sealed class MovieListNavigation {
        data class ShowMovieError(val error: Int) : MovieListNavigation()
        data class ShowMovieList(val movieList: List<MovieDomain>) : MovieListNavigation()
        object HideLoading : MovieListNavigation()
        object ShowLoading : MovieListNavigation()
    }

    companion object {
        private const val PAGE_SIZE = 20
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}