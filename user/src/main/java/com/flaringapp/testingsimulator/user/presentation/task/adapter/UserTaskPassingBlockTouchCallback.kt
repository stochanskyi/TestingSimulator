package com.flaringapp.testingsimulator.user.presentation.task.adapter

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class UserTaskPassingBlockTouchCallback : ItemTouchHelper.Callback() {

    private lateinit var adapter: UserTaskPassingBlocksTouchHelperAdapter

    fun attach(adapter: UserTaskPassingBlocksTouchHelperAdapter) {
        this.adapter = adapter
    }

    override fun isLongPressDragEnabled(): Boolean = true

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
        if (viewHolder !is UserTaskPassingBlockDraggableViewHolder) return
        if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
            viewHolder.onDragStarted()
        }
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        if (viewHolder !is UserTaskPassingBlockDraggableViewHolder) return
        viewHolder.onDragEnded()
    }

    private fun Int.takeIfCorrect() = takeIf { it != RecyclerView.NO_POSITION }
}