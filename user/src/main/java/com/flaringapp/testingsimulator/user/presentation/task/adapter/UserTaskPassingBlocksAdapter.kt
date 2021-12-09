package com.flaringapp.testingsimulator.user.presentation.task.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.flaringapp.testingsimulator.user.presentation.task.models.UserTaskPassingBlockViewData

class UserTaskPassingBlocksAdapter(
    private val dragListener: UserTaskPassingBlockTouchDragListener,
    private val onEnabledChanged: (id: Int, isEnabled: Boolean) -> Unit,
    private val onMove: (id: Int, fromPosition: Int, toPosition: Int) -> Unit,
): ListAdapter<UserTaskPassingBlockViewData, UserTaskPassingBlockViewHolder>(
    UserTaskPassingBlocksDiffCallback()
), UserTaskPassingBlocksTouchHelperAdapter {

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
            onEnabledChanged = onEnabledChanged,
        )
    }

    override fun canDrag(position: Int): Boolean {
        return getItem(position).isEnabled
    }

    override fun canMove(fromPosition: Int, toPosition: Int): Boolean {
        return getItem(fromPosition).isEnabled && getItem(toPosition).isEnabled
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        val item = getItem(fromPosition)
        onMove(item.id, fromPosition, toPosition)
    }
}