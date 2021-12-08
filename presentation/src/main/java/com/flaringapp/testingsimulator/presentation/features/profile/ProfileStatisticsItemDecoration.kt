package com.flaringapp.testingsimulator.presentation.features.profile

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.flaringapp.testingsimulator.core.presentation.utils.dpi

class ProfileStatisticsItemDecoration : RecyclerView.ItemDecoration() {

    companion object {
        private const val OFFSET_TOP = 8
        private const val OFFSET_HORIZONTAL_INNER = 8
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position == RecyclerView.NO_POSITION) return

        val context = parent.context

        if (position > 1) {
            outRect.top = context.dpi(OFFSET_TOP)
        }

        val isEven = position % 2 == 0
        if (isEven) {
            val isLast = position == parent.adapter?.itemCount
            outRect.applyLeftInset(context, isLast)
        } else {
            outRect.applyRightInset(context)
        }
    }

    private fun Rect.applyLeftInset(context: Context, isLast: Boolean) {
        if (isLast) return
        right = context.dpi(OFFSET_HORIZONTAL_INNER)
    }

    private fun Rect.applyRightInset(context: Context) {
        left = context.dpi(OFFSET_HORIZONTAL_INNER)
    }
}