package com.flaringapp.testingsimulator.admin.presentation.test

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.flaringapp.testingsimulator.admin.presentation.test.adapter.AdminTestItemsAdapter
import com.flaringapp.testingsimulator.core.presentation.utils.dpi

class AdminTestItemsTaskSpacingDecoration : RecyclerView.ItemDecoration() {

    companion object {
        private const val TOP_SPACING = 8

        private val taskViewTypes = listOf(
            AdminTestItemsAdapter.VIEW_TYPE_TASK,
            AdminTestItemsAdapter.VIEW_TYPE_ADD_TASK,
        )
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

        val viewType = parent.adapter?.getItemViewType(position) ?: return
        if (viewType !in taskViewTypes) return

        val viewTypeAbove = parent.adapter?.getItemViewType(position) ?: return
        if (viewTypeAbove !in taskViewTypes) return

        outRect.top = parent.context.dpi(TOP_SPACING)
    }
}