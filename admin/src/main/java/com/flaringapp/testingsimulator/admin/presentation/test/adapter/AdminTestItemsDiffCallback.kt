package com.flaringapp.testingsimulator.admin.presentation.test.adapter

import androidx.recyclerview.widget.DiffUtil
import com.flaringapp.testingsimulator.admin.presentation.test.models.AdminTestAddBlockViewData
import com.flaringapp.testingsimulator.admin.presentation.test.models.AdminTestBlockViewData
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
            oldItem is AdminTestBlockViewData && newItem is AdminTestBlockViewData -> {
                oldItem.id == newItem.id
            }
            oldItem is AdminTestAddBlockViewData && newItem is AdminTestAddBlockViewData -> true
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