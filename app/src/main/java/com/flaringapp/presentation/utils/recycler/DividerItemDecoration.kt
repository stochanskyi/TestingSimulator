package com.flaringapp.presentation.utils.recycler

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class DividerItemDecoration(
    private val drawable: Drawable,
    spacing: Int = 0
) : SpacingItemDecoration(spacing) {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        if (parent.getChildLayoutPosition(view) != 0) {
            outRect.top += drawable.intrinsicHeight
        }
    }

    override fun onDraw(
        canvas: Canvas,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val dividerLeft = parent.paddingLeft
        val dividerRight = parent.width - parent.paddingRight
        val childCount = parent.childCount

        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val dividerTop = child.bottom + params.bottomMargin
            val dividerBottom: Int = dividerTop + drawable.intrinsicHeight

            drawable.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)

            drawable.draw(canvas)
        }
    }
}