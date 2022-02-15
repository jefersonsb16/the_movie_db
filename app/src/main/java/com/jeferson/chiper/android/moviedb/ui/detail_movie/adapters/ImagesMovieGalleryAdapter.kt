package com.jeferson.chiper.android.moviedb.ui.detail_movie.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeferson.chiper.android.moviedb.R
import com.jeferson.chiper.android.moviedb.databinding.ItemImagesMovieBinding
import com.jeferson.chiper.android.moviedb.domain.ImageMovieDomain
import com.jeferson.chiper.android.moviedb.framework.server.ApiConstants.BASE_IMAGE_W500_URL
import com.jeferson.chiper.android.moviedb.ui.common.bindImageUrl

class ImagesMovieGalleryAdapter :
    RecyclerView.Adapter<ImagesMovieGalleryAdapter.ImagesMovieViewHolder>() {

    private val imagesList: MutableList<ImageMovieDomain> = mutableListOf()

    fun setDataImages(list: List<ImageMovieDomain>) {
        imagesList.clear()
        imagesList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesMovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_images_movie, parent, false)
        return ImagesMovieViewHolder(view)
    }

    override fun getItemCount(): Int = imagesList.size

    override fun onBindViewHolder(holder: ImagesMovieViewHolder, position: Int) {
        holder.bind(imagesList[position])
    }

    class ImagesMovieViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {
        private val binding = ItemImagesMovieBinding.bind(view)

        fun bind(item: ImageMovieDomain) {
            binding.imageBackdropMovie.bindImageUrl(
                url = BASE_IMAGE_W500_URL + item.file_path,
                placeholder = R.drawable.ic_camera,
                errorPlaceholder = R.drawable.ic_broken_image
            )
        }
    }
}