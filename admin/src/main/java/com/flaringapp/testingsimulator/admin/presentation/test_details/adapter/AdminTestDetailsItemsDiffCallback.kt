package com.flaringapp.testingsimulator.admin.presentation.test_details.adapter

import androidx.recyclerview.widget.DiffUtil
import com.flaringapp.testingsimulator.admin.presentation.test_details.models.AdminTestDetailsAddTaskViewData
import com.flaringapp.testingsimulator.admin.presentation.test_details.models.AdminTestDetailsTaskViewData
import com.flaringapp.testingsimulator.admin.presentation.test_details.models.AdminTestDetailsHeaderViewData
import com.flaringapp.testingsimulator.admin.presentation.test_details.models.AdminTestDetailsItemViewData

class AdminTestDetailsItemsDiffCallback : DiffUtil.ItemCallback<AdminTestDetailsItemViewData>() {

    override fun areItemsTheSame(
        oldItem: AdminTestDetailsItemViewData,
        newItem: AdminTestDetailsItemViewData
    ): Boolean {
        return when {
            oldItem is AdminTestDetailsHeaderViewData && newItem is AdminTestDetailsHeaderViewData -> {
                oldItem.name == newItem.name
            }
            oldItem is AdminTestDetailsTaskViewData && newItem is AdminTestDetailsTaskViewData -> {
                oldItem.id == newItem.id
            }
            oldItem is AdminTestDetailsAddTaskViewData && newItem is AdminTestDetailsAddTaskViewData -> true
            else -> false
        }
    }

    override fun areContentsTheSame(
        oldItem: AdminTestDetailsItemViewData,
        newItem: AdminTestDetailsItemViewData
    ): Boolean {
        return oldItem == newItem
    }
}