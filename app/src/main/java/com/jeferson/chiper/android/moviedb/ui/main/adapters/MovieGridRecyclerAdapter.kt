package com.jeferson.chiper.android.moviedb.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeferson.chiper.android.moviedb.R
import com.jeferson.chiper.android.moviedb.databinding.ItemGridMovieBinding
import com.jeferson.chiper.android.moviedb.domain.MovieDomain
import com.jeferson.chiper.android.moviedb.framework.server.ApiConstants.BASE_IMAGE_W500_URL
import com.jeferson.chiper.android.moviedb.ui.common.bindImageUrl
import com.jeferson.chiper.android.moviedb.ui.main.OnItemMovieClickListener

class MovieGridRecyclerAdapter(
    var listenerOpenDetail: OnItemMovieClickListener,
) : RecyclerView.Adapter<MovieGridRecyclerAdapter.MovieGridViewHolder>() {

    private val popularMoviesList: MutableList<MovieDomain> = mutableListOf()

    fun setDataList(movieList: List<MovieDomain>) {
        popularMoviesList.clear()
        popularMoviesList.addAll(movieList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieGridViewHolder {
        val inflater = LayoutInflater.from(parent.context);
        val view = inflater.inflate(R.layout.item_grid_movie, parent, false)

        return MovieGridViewHolder(view, listenerOpenDetail)
    }

    override fun getItemCount(): Int = popularMoviesList.size

    override fun getItemId(position: Int): Long = popularMoviesList[position].id.toLong()

    override fun onBindViewHolder(holder: MovieGridViewHolder, position: Int) {
        holder.bind(popularMoviesList[position])
    }

    class MovieGridViewHolder(
        view: View,
        private val listenerOpenDetail: OnItemMovieClickListener
    ) : RecyclerView.ViewHolder(view) {

        private val binding = ItemGridMovieBinding.bind(view)

        fun bind(item: MovieDomain) {
            binding.movie = item

            binding.movieImage.bindImageUrl(
                url = BASE_IMAGE_W500_URL + item.poster_path,
                placeholder = R.drawable.ic_camera,
                errorPlaceholder = R.drawable.ic_broken_image
            )

            itemView.setOnClickListener { listenerOpenDetail.openMovieDetail(item) }
        }
    }
}