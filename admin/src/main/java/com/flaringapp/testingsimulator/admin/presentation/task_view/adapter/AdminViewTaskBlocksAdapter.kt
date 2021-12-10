package com.flaringapp.testingsimulator.admin.presentation.task_view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.flaringapp.testingsimulator.admin.presentation.task_view.models.AdminViewTaskBlockViewData

class AdminViewTaskBlocksAdapter : ListAdapter<
    AdminViewTaskBlockViewData,
    AdminViewTaskBlockViewHolder>(
    AdminTestViewBlocksDiffCallback()
) {

    private var items: MutableList<AdminViewTaskBlockViewData> = mutableListOf()

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdminViewTaskBlockViewHolder {
        return AdminViewTaskBlockViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: AdminViewTaskBlockViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}