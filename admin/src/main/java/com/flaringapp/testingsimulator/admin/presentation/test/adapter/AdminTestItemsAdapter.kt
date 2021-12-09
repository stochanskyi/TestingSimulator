package com.flaringapp.testingsimulator.admin.presentation.test.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.flaringapp.testingsimulator.admin.presentation.test.models.AdminTestAddTaskViewData
import com.flaringapp.testingsimulator.admin.presentation.test.models.AdminTestTaskViewData
import com.flaringapp.testingsimulator.admin.presentation.test.models.AdminTestHeaderViewData
import com.flaringapp.testingsimulator.admin.presentation.test.models.AdminTestListItemViewData
import com.flaringapp.testingsimulator.core.app.common.Action

class AdminTestItemsAdapter(
    private val addTask: Action,
) : ListAdapter<AdminTestListItemViewData, AdminTestItemViewHolder>(
    AdminTestItemsDiffCallback()
) {

    companion object {
        const val VIEW_TYPE_HEADER = 10
        const val VIEW_TYPE_TASK = 20
        const val VIEW_TYPE_ADD_TASK = 21
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is AdminTestHeaderViewData -> VIEW_TYPE_HEADER
            is AdminTestTaskViewData -> VIEW_TYPE_TASK
            is AdminTestAddTaskViewData -> VIEW_TYPE_ADD_TASK
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminTestItemViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> AdminTestHeaderViewHolder.create(parent)
            VIEW_TYPE_TASK -> AdminTestTaskViewHolder.create(parent)
            VIEW_TYPE_ADD_TASK -> AdminTestAddTaskViewHolder.create(parent)
            else -> error("Unsupported view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: AdminTestItemViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is AdminTestHeaderViewHolder -> holder.bind(
                item as AdminTestHeaderViewData
            )
            is AdminTestTaskViewHolder -> holder.bind(
                item as AdminTestTaskViewData
            )
            is AdminTestAddTaskViewHolder -> holder.bind(addTask)
        }
    }

    override fun onViewRecycled(holder: AdminTestItemViewHolder) {
        super.onViewRecycled(holder)
        holder.release()
    }
}