package com.flaringapp.testingsimulator.user.presentation.task.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flaringapp.testingsimulator.user.presentation.task.models.UserTaskPassingBlockViewData

class UserTaskPassingBlocksAdapter(
    private val dragListener: UserTaskPassingBlockTouchDragListener,
    private val onBlockActiveChanged: (id: Int, isActive: Boolean) -> Unit,
    private val onMove: (id: Int, fromPosition: Int, toPosition: Int) -> Unit,
): RecyclerView.Adapter<UserTaskPassingBlockViewHolder>(),
    UserTaskPassingBlocksTouchHelperAdapter {

    private var items: MutableList<UserTaskPassingBlockViewData> = mutableListOf()

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserTaskPassingBlockViewHolder {
        return UserTaskPassingBlockViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: UserTaskPassingBlockViewHolder, position: Int) {
        holder.bind(
            dragListener = dragListener,
            item = getItem(position),
            onBlockActiveChanged = onBlockActiveChanged,
        )
    }

    override fun canDrag(position: Int): Boolean {
        return getItem(position).isBlockActive
    }

    override fun canMove(fromPosition: Int, toPosition: Int): Boolean {
        return getItem(fromPosition).isBlockActive && getItem(toPosition).isBlockActive
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        val item = items.removeAt(fromPosition)
        items.add(toPosition, item)
        notifyItemMoved(fromPosition, toPosition)
        onMove(item.id, fromPosition, toPosition)
    }

    fun setItems(newItems: MutableList<UserTaskPassingBlockViewData>) {
        val oldItems = items
        items = newItems
        notifyItemRangeRemoved(0, oldItems.size)
        notifyItemRangeInserted(0, newItems.size)
    }

    private fun getItem(position: Int) = items[position]
}