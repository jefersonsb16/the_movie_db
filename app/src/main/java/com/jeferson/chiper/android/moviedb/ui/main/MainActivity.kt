package com.jeferson.chiper.android.moviedb.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
import com.jeferson.chiper.android.moviedb.ui.detail_movie.DetailMovieActivity
import com.jeferson.chiper.android.moviedb.ui.main.adapters.MovieGridRecyclerAdapter
import com.jeferson.chiper.android.moviedb.utils.Constants.MOVIE_ID
import com.jeferson.chiper.android.moviedb.utils.Constants.MOVIE_IMAGE
import com.jeferson.chiper.android.moviedb.utils.Constants.MOVIE_OVERVIEW
import com.jeferson.chiper.android.moviedb.utils.Constants.MOVIE_RELEASE_DATE
import com.jeferson.chiper.android.moviedb.utils.Constants.MOVIE_TITLE
import com.jeferson.chiper.android.moviedb.utils.Constants.TYPE_API
import com.jeferson.chiper.android.moviedb.utils.Constants.TYPE_LOCAL
import com.jeferson.chiper.android.moviedb.utils.MessageErrorFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnItemMovieClickListener {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: PopularMoviesViewModel by viewModels()
    private lateinit var movieGridAdapter: MovieGridRecyclerAdapter
    private val messageErrorFactory = MessageErrorFactory()

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
        event?.peekContent()?.let { navigation ->
            when (navigation) {
                is PopularMoviesViewModel.MovieListNavigation.ShowMovieError -> navigation.run {
                    messageErrorFactory.showSnackBar(this@MainActivity, error, binding.root)
                }
                is PopularMoviesViewModel.MovieListNavigation.ShowMovieList -> navigation.run {
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
        val goDetail = Intent(this@MainActivity, DetailMovieActivity::class.java)
        goDetail.putExtra(MOVIE_ID, movie.id)
        goDetail.putExtra(MOVIE_TITLE, movie.title)
        goDetail.putExtra(MOVIE_RELEASE_DATE, movie.release_date)
        goDetail.putExtra(MOVIE_IMAGE, movie.backdrop_path)
        goDetail.putExtra(MOVIE_OVERVIEW, movie.overview)
        startActivity(goDetail)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_filters_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.actionMostPopular -> {
                mainViewModel.setValueTypeGetData(TYPE_API)
                mainViewModel.onGetPopularMovies()
                true
            }
            R.id.actionLocal -> {
                mainViewModel.setValueTypeGetData(TYPE_LOCAL)
                mainViewModel.onGetAllLocalMovies()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}