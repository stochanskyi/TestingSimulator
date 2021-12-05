package com.flaringapp.data.color

import android.content.Context
import androidx.core.content.ContextCompat

class ColorProviderImpl(
    private val context: Context,
) : ColorProvider {

    override fun getColor(colorRes: Int): Int {
        return ContextCompat.getColor(context, colorRes)
    }
}