@file:Suppress("unused")

package com.flaringapp.presentation.utils.recycler

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

open class DividerItemDecoration(
    private val drawable: Drawable,
    spacing: Int = 0,
    private val paddingLeft: Int? = null,
    private val paddingRight: Int? = null,
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
        val dividerLeft = paddingLeft ?: parent.paddingLeft
        val dividerRight = parent.width - (paddingRight ?: parent.paddingRight)
        val childCount = parent.childCount

        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i)
            if (!canDrawOnChild(child, parent, state)) continue

            val params = child.layoutParams as RecyclerView.LayoutParams

            val dividerTop = child.bottom + params.bottomMargin
            val dividerBottom: Int = dividerTop + drawable.intrinsicHeight

            drawable.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)

            drawable.draw(canvas)
        }
    }

    protected open fun canDrawOnChild(
        child: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ): Boolean = true

    class Builder {

        private var drawable: Drawable? = null
        private var spacing: Int = 0
        private var paddingLeft = 0
        private var paddingRight = 0

        fun drawable(drawable: Drawable) = apply {
            this.drawable = drawable
        }

        fun drawable(context: Context, drawableRes: Int) = apply {
            this.drawable = ContextCompat.getDrawable(context, drawableRes)
        }

        fun spacing(spacing: Int) = apply {
            this.spacing = spacing
        }

        fun horizontalPadding(padding: Int) = apply {
            this.paddingLeft = padding
            this.paddingRight = padding
        }

        fun paddingLeft(paddingLeft: Int) = apply {
            this.paddingLeft = paddingLeft
        }

        fun paddingRight(paddingRight: Int) = apply {
            this.paddingRight = paddingRight
        }

        fun build() : DividerItemDecoration {
            return DividerItemDecoration(
                requireNotNull(drawable),
                spacing,
                paddingLeft,
                paddingRight
            )
        }

    }

}