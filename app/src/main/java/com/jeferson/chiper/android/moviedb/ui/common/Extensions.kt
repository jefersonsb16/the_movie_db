package com.jeferson.chiper.android.moviedb.ui.common

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide

fun ImageView.bindImageUrl(
    url: String?, @DrawableRes placeholder: Int,
    @DrawableRes errorPlaceholder: Int
) {
    if (url.isNullOrBlank()) {
        setImageResource(placeholder)
        return
    }

    Glide.with(context)
        .load(url)
        .error(errorPlaceholder)
        .placeholder(placeholder)
        .into(this)
}