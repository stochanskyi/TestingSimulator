package com.flaringapp.testingsimulator.admin.presentation.task_edit.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flaringapp.testingsimulator.admin.presentation.task_edit.models.AdminTaskEditBlockViewData
import com.flaringapp.testingsimulator.presentation.common.recycler.drag.TouchDragListener

class AdminTaskEditBlocksAdapter(
    private val dragListener: TouchDragListener,
    private val onBlockTextChanged: (id: Int, text: String) -> Unit,
    private val onBlockActiveChanged: (id: Int, isActive: Boolean) -> Unit,
    private val onMove: (id: Int, fromPosition: Int, toPosition: Int) -> Unit,
    private val onLink: (id: Int) -> Unit,
    private val onRemove: (id: Int) -> Unit,
) : RecyclerView.Adapter<AdminTaskEditBlockViewHolder>(),
    AdminTaskEditBlocksTouchHelperAdapter {

    private var items: MutableList<AdminTaskEditBlockViewData> = mutableListOf()

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdminTaskEditBlockViewHolder {
        return AdminTaskEditBlockViewHolder.create(parent)
    }

    override fun onBindViewHolder(
        holder: AdminTaskEditBlockViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
            return
        }

        val item = getItem(position)
        payloads.forEach { payload ->
            when (payload) {
                LINKED_CHANGED -> holder.setIsLinked(item.isLinked)
            }
        }
    }

    override fun onBindViewHolder(holder: AdminTaskEditBlockViewHolder, position: Int) {
        holder.bind(
            dragListener = dragListener,
            item = getItem(position),
            onTextChanged = onBlockTextChanged,
            onBlockActiveChanged = { id, isActive -> onBlockActiveChange(holder, id, isActive) },
            onLinkClicked = onLink,
            onRemoveClicked = onRemove,
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

    fun setItems(newItems: MutableList<AdminTaskEditBlockViewData>) {
        val oldItems = items
        items = newItems
        notifyItemRangeRemoved(0, oldItems.size)
        notifyItemRangeInserted(0, newItems.size)
    }

    fun removeItemAtPosition(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun changeItemIsLinked(position: Int, isLinked: Boolean) {
        val item = items[position]
        item.isLinked = isLinked
        notifyItemChanged(position, LINKED_CHANGED)
    }

    private fun onBlockActiveChange(
        viewHolder: AdminTaskEditBlockViewHolder,
        id: Int,
        isActive: Boolean
    ) {
        val oldPosition = viewHolder.adapterPosition
        val newPosition = getNewPosition(isActive)

        items[oldPosition].isBlockActive = isActive
        onBlockActiveChanged(id, isActive)

        if (oldPosition == newPosition) return

        onItemMove(oldPosition, newPosition)
    }

    private fun getNewPosition(isActive: Boolean): Int {
        return if (isActive) {
            (items.indexOfLast { it.isBlockActive }.takeIf { it > 0 } ?: -1) + 1
        } else {
            items.size - 1
        }
    }

    private fun getItem(position: Int) = items[position]

    companion object {
        private const val LINKED_CHANGED = "linked_changed"
    }
}