package com.flaringapp.testingsimulator.core.data.color

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat

class ColorProviderImpl(
    private val context: Context,
) : ColorProvider {

    override fun getColor(colorRes: Int): Int {
        return ContextCompat.getColor(context, colorRes)
    }

    override fun transparent(): Int = Color.TRANSPARENT
}