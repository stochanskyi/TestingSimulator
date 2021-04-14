package com.flaringapp.presentation.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.clearGlide() {
    Glide.with(this)
        .clear(this)
}