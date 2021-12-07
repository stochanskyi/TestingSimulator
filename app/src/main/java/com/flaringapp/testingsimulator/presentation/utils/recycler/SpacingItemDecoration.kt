package com.flaringapp.testingsimulator.presentation.utils.recycler

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class SpacingItemDecoration(
    spacing: Int = 0
) : RecyclerView.ItemDecoration() {

    private val halfSpacing = spacing / 2

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        when (parent.getChildLayoutPosition(view)) {
            0 -> outRect.bottom = halfSpacing
            state.itemCount - 1 -> outRect.top = halfSpacing
            else -> {
                outRect.top = halfSpacing
                outRect.bottom = halfSpacing
            }
        }
    }
}