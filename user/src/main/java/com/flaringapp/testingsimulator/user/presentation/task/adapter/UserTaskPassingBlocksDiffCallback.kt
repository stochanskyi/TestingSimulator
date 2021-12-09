package com.flaringapp.testingsimulator.user.presentation.task.adapter

import androidx.recyclerview.widget.DiffUtil
import com.flaringapp.testingsimulator.user.presentation.task.models.UserTaskPassingBlockViewData

class UserTaskPassingBlocksDiffCallback: DiffUtil.ItemCallback<UserTaskPassingBlockViewData>() {

    override fun areItemsTheSame(
        oldItem: UserTaskPassingBlockViewData,
        newItem: UserTaskPassingBlockViewData
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: UserTaskPassingBlockViewData,
        newItem: UserTaskPassingBlockViewData
    ): Boolean {
        return oldItem == newItem
    }
}