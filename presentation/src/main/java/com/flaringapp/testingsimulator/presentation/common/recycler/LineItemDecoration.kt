package com.flaringapp.testingsimulator.presentation.common.recycler

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.flaringapp.testingsimulator.presentation.R

class LineItemDecoration(
    context: Context?,
    orientation: Int = VERTICAL
) : DividerItemDecoration(context, orientation) {

    init {
        context?.let { initDrawable(it) }
    }

    private fun initDrawable(context: Context) {
        val drawable = ContextCompat.getDrawable(context, R.drawable.bg_separator_padded) ?: return

        setDrawable(drawable)
    }

}