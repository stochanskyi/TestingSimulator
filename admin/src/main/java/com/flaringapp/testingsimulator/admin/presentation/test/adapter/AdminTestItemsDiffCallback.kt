package com.flaringapp.testingsimulator.admin.presentation.test.adapter

import androidx.recyclerview.widget.DiffUtil
import com.flaringapp.testingsimulator.admin.presentation.test.models.AdminTestAddTaskViewData
import com.flaringapp.testingsimulator.admin.presentation.test.models.AdminTestTaskViewData
import com.flaringapp.testingsimulator.admin.presentation.test.models.AdminTestHeaderViewData
import com.flaringapp.testingsimulator.admin.presentation.test.models.AdminTestListItemViewData

class AdminTestItemsDiffCallback : DiffUtil.ItemCallback<AdminTestListItemViewData>() {

    override fun areItemsTheSame(
        oldItem: AdminTestListItemViewData,
        newItem: AdminTestListItemViewData
    ): Boolean {
        return when {
            oldItem is AdminTestHeaderViewData && newItem is AdminTestHeaderViewData -> {
                oldItem.name == newItem.name
            }
            oldItem is AdminTestTaskViewData && newItem is AdminTestTaskViewData -> {
                oldItem.id == newItem.id
            }
            oldItem is AdminTestAddTaskViewData && newItem is AdminTestAddTaskViewData -> true
            else -> false
        }
    }

    override fun areContentsTheSame(
        oldItem: AdminTestListItemViewData,
        newItem: AdminTestListItemViewData
    ): Boolean {
        return oldItem == newItem
    }
}