package com.flaringapp.testingsimulator.admin.presentation.task_edit

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.flaringapp.testingsimulator.core.presentation.utils.dpi

class AdminTaskEditBlockItemDecoration : RecyclerView.ItemDecoration() {

    companion object {
        private const val BLOCK_OFFSET_TOP = 8
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position == RecyclerView.NO_POSITION || position == 0) return

        outRect.top = parent.context.dpi(BLOCK_OFFSET_TOP)
    }
}