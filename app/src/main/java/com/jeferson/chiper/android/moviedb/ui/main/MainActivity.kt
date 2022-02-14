package com.jeferson.chiper.android.moviedb.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jeferson.chiper.android.moviedb.R
import com.jeferson.chiper.android.moviedb.databinding.ActivityMainBinding
import com.jeferson.chiper.android.moviedb.domain.MovieDomain
import com.jeferson.chiper.android.moviedb.ui.common.Event
import com.jeferson.chiper.android.moviedb.ui.main.adapters.MovieGridRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnItemMovieClickListener {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: PopularMoviesViewModel by viewModels()
    private lateinit var movieGridAdapter: MovieGridRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this@MainActivity
        binding.viewModel = mainViewModel

        setSupportActionBar(binding.toolbarMain)
        movieGridAdapter = MovieGridRecyclerAdapter(
            this
        )
        movieGridAdapter.setHasStableIds(true)

        binding.rvMovieList.run {
            adapter = movieGridAdapter
            addOnScrollListener(onScrollListener)
        }
        mainViewModel.events.observe(this, Observer(this::updateUI))
        mainViewModel.onGetPopularMovies()

        // add swipe refresh event
        binding.swipeRefreshMovieList.setOnRefreshListener {
            mainViewModel.onRetryGetPopularMovies()
        }
    }

    // listener for load more movies
    private val onScrollListener: RecyclerView.OnScrollListener by lazy {
        object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val visibleItemCount: Int = layoutManager.childCount
                val totalItemCount: Int = layoutManager.itemCount
                val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()

                mainViewModel.onLoadMoreMovies(
                    visibleItemCount,
                    firstVisibleItemPosition,
                    totalItemCount
                )
            }
        }
    }

    private fun updateUI(event: Event<PopularMoviesViewModel.MovieListNavigation>?) {
        event?.getContentIfNotHandled()?.let { navigation ->
            when (navigation) {
                is PopularMoviesViewModel.MovieListNavigation.ShowMovieError -> navigation.run {
                    //
                }
                is PopularMoviesViewModel.MovieListNavigation.ShowMovieList -> navigation.run {
                    // movieGridAdapter.popularMoviesList = movieList
                    movieGridAdapter.setDataList(movieList)
                }
                PopularMoviesViewModel.MovieListNavigation.HideLoading -> {
                    binding.swipeRefreshMovieList.isRefreshing = false
                }
                PopularMoviesViewModel.MovieListNavigation.ShowLoading -> {
                    binding.swipeRefreshMovieList.isRefreshing = true
                }
            }
        }
    }

    override fun openMovieDetail(movie: MovieDomain) {
        // not implemented
    }
}