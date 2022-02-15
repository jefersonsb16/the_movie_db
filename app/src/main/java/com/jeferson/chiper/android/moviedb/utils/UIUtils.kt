package com.jeferson.chiper.android.moviedb.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter

object UIUtils {

    @JvmStatic
    @BindingAdapter("loadTextOverview")
    fun loadTextOverview(textView: TextView, text: String?) {
        if (text?.isEmpty() != false)
            textView.text = "N/A"
        else
            textView.text = text
    }
}