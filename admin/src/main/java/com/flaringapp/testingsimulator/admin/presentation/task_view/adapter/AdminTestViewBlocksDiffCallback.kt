package com.flaringapp.testingsimulator.admin.presentation.task_view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.flaringapp.testingsimulator.admin.presentation.task_view.models.AdminViewTaskBlockViewData

class AdminTestViewBlocksDiffCallback : DiffUtil.ItemCallback<AdminViewTaskBlockViewData>() {

    override fun areItemsTheSame(
        oldItem: AdminViewTaskBlockViewData,
        newItem: AdminViewTaskBlockViewData
    ): Boolean {
        return oldItem.text == newItem.text
    }

    override fun areContentsTheSame(
        oldItem: AdminViewTaskBlockViewData,
        newItem: AdminViewTaskBlockViewData
    ): Boolean {
        return oldItem == newItem
    }
}