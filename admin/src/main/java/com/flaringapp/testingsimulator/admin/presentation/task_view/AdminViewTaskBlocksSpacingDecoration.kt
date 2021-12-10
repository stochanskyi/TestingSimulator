package com.flaringapp.testingsimulator.admin.presentation.task_view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.flaringapp.testingsimulator.core.presentation.utils.dpi

class AdminViewTaskBlocksSpacingDecoration : RecyclerView.ItemDecoration() {

    companion object {
        private const val TOP_SPACING = 8
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position == RecyclerView.NO_POSITION) return
        if (position == 0) return

        outRect.top = parent.context.dpi(TOP_SPACING)
    }
}