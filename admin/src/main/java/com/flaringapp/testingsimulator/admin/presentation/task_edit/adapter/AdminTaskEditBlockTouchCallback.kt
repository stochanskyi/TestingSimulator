package com.flaringapp.testingsimulator.admin.presentation.task_edit.adapter

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.flaringapp.testingsimulator.presentation.common.recycler.drag.DraggableViewHolderContract

class AdminTaskEditBlockTouchCallback : ItemTouchHelper.Callback() {

    private lateinit var adapter: AdminTaskEditBlocksTouchHelperAdapter

    fun attach(adapter: AdminTaskEditBlocksTouchHelperAdapter) {
        this.adapter = adapter
    }

    override fun isLongPressDragEnabled(): Boolean = false

    override fun isItemViewSwipeEnabled(): Boolean = false

    override fun canDropOver(
        recyclerView: RecyclerView,
        current: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val fromPosition = current.adapterPosition.takeIfCorrect() ?: return false
        val toPosition = target.adapterPosition.takeIfCorrect() ?: return false
        return adapter.canMove(fromPosition, toPosition)
    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val position = viewHolder.adapterPosition.takeIfCorrect()
        val flags =
            if (position == null || !adapter.canDrag(position)) 0
            else ItemTouchHelper.UP or ItemTouchHelper.DOWN
        return makeMovementFlags(flags, 0)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val fromPosition = viewHolder.adapterPosition.takeIfCorrect() ?: return false
        val toPosition = target.adapterPosition.takeIfCorrect() ?: return false

        adapter.onItemMove(fromPosition, toPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (viewHolder !is DraggableViewHolderContract) return
        if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
            viewHolder.onDragStarted()
        }
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        if (viewHolder !is DraggableViewHolderContract) return
        viewHolder.onDragEnded()
    }

    private fun Int.takeIfCorrect() = takeIf { it != RecyclerView.NO_POSITION }
}