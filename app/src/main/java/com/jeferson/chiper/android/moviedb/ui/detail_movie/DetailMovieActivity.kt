package com.jeferson.chiper.android.moviedb.ui.detail_movie

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.jeferson.chiper.android.moviedb.R
import com.jeferson.chiper.android.moviedb.databinding.ActivityDetailMovieBinding
import com.jeferson.chiper.android.moviedb.domain.MovieDomain
import com.jeferson.chiper.android.moviedb.framework.server.ApiConstants.BASE_IMAGE_ORIGINAL_URL
import com.jeferson.chiper.android.moviedb.ui.common.Event
import com.jeferson.chiper.android.moviedb.ui.common.bindImageUrl
import com.jeferson.chiper.android.moviedb.ui.detail_movie.adapters.ImagesMovieGalleryAdapter
import com.jeferson.chiper.android.moviedb.utils.Constants.MOVIE_TITLE
import com.jeferson.chiper.android.moviedb.utils.MessageErrorFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {

    private val messageErrorFactory = MessageErrorFactory()
    private val imagesMovieGalleryAdapter by lazy { ImagesMovieGalleryAdapter() }

    // properties for show or hide toolbar title
    private var isToolbarShow = true
    private var scrollRange = -1

    private lateinit var binding: ActivityDetailMovieBinding
    private val detailMovieViewModel: DetailMovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_movie)
        binding.lifecycleOwner = this@DetailMovieActivity
        binding.viewModel = detailMovieViewModel

        // set toolbar support and listen to scroll when is collapsed
        setSupportActionBar(binding.toolbarDetailMovie)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.toolbarDetailMovie.setNavigationOnClickListener { finish() }

        setListenerScrollToolbar()

        binding.rvImagesMovieList.run {
            adapter = imagesMovieGalleryAdapter
            layoutManager = LinearLayoutManager(this@DetailMovieActivity, LinearLayoutManager.HORIZONTAL, false)
        }

        detailMovieViewModel.movieValues.observe(this, Observer(this::loadMovieValues))
        detailMovieViewModel.events.observe(this, Observer(this::updateUI))
        detailMovieViewModel.initializeMovieLiveData()

        binding.tvRetryDetailMovie.setOnClickListener {
            detailMovieViewModel.initializeMovieLiveData()
        }
    }

    private fun setListenerScrollToolbar() {
        val movieTitle = intent.getStringExtra(MOVIE_TITLE)

        binding.appBarDetailMovie.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1) {
                scrollRange = barLayout?.totalScrollRange!!
            }

            if (scrollRange + verticalOffset == 0) {
                binding.collapsingToolbarDetail.setCollapsedTitleTextColor(Color.rgb(255, 255, 255))
                binding.collapsingToolbarDetail.title = movieTitle ?: getString(R.string.title_detail)

                isToolbarShow = true
            } else if (isToolbarShow) {
                binding.collapsingToolbarDetail.title = " " // space between double-quote is required
                isToolbarShow = false
            }
        })
    }

    private fun loadMovieValues(movie: MovieDomain) {
        binding.imageDetailMovie.bindImageUrl(
            url = BASE_IMAGE_ORIGINAL_URL + movie.backdrop_path,
            placeholder = R.drawable.ic_camera,
            errorPlaceholder = R.drawable.ic_broken_image
        )

        binding.movieDataTitle = movie.title
        binding.movieDataOverview = movie.overview
        binding.movieDataReleaseDate = movie.release_date
    }

    private fun updateUI(event: Event<DetailMovieViewModel.MovieDetailNavigation>?) {
        event?.getContentIfNotHandled()?.let { navigation ->
            when (navigation) {
                DetailMovieViewModel.MovieDetailNavigation.CloseActivity -> {
                    Toast.makeText(
                        this@DetailMovieActivity,
                        getString(R.string.message_not_movie),
                        Toast.LENGTH_LONG
                    ).show()

                    finish()
                }
                DetailMovieViewModel.MovieDetailNavigation.HideImagesMovieLoading -> {
                    binding.loadingImagesMovie.visibility = View.INVISIBLE
                }
                DetailMovieViewModel.MovieDetailNavigation.ShowImagesMovieLoading -> {
                    binding.loadingImagesMovie.visibility = View.VISIBLE
                }
                is DetailMovieViewModel.MovieDetailNavigation.ShowImagesMovieError -> navigation.run {
                    messageErrorFactory.showSnackBar(this@DetailMovieActivity, error, binding.root)
                }
                is DetailMovieViewModel.MovieDetailNavigation.ShowImagesMovieList -> navigation.run {
                    imagesMovieGalleryAdapter.setDataImages(imagesMovieList)
                }
            }
        }
    }
}