package com.flaringapp.testingsimulator.admin.presentation.test_details.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.flaringapp.testingsimulator.admin.presentation.test_details.models.AdminTestDetailsAddTaskViewData
import com.flaringapp.testingsimulator.admin.presentation.test_details.models.AdminTestDetailsTaskViewData
import com.flaringapp.testingsimulator.admin.presentation.test_details.models.AdminTestDetailsHeaderViewData
import com.flaringapp.testingsimulator.admin.presentation.test_details.models.AdminTestDetailsItemViewData
import com.flaringapp.testingsimulator.core.app.common.Action

class AdminTestDetailsItemsAdapter(
    private val openTask: (Int) -> Unit,
    private val addTask: Action,
) : ListAdapter<AdminTestDetailsItemViewData, AdminTestDetailsItemViewHolder>(
    AdminTestDetailsItemsDiffCallback()
) {

    companion object {
        const val VIEW_TYPE_HEADER = 10
        const val VIEW_TYPE_TASK = 20
        const val VIEW_TYPE_ADD_TASK = 21
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is AdminTestDetailsHeaderViewData -> VIEW_TYPE_HEADER
            is AdminTestDetailsTaskViewData -> VIEW_TYPE_TASK
            is AdminTestDetailsAddTaskViewData -> VIEW_TYPE_ADD_TASK
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminTestDetailsItemViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> AdminTestDetailsHeaderViewHolder.create(parent)
            VIEW_TYPE_TASK -> AdminTestDetailsTaskViewHolder.create(parent)
            VIEW_TYPE_ADD_TASK -> AdminTestDetailsAddTaskViewHolder.create(parent)
            else -> error("Unsupported view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: AdminTestDetailsItemViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is AdminTestDetailsHeaderViewHolder -> holder.bind(
                item as AdminTestDetailsHeaderViewData
            )
            is AdminTestDetailsTaskViewHolder -> holder.bind(
                item as AdminTestDetailsTaskViewData,
                openTask,
            )
            is AdminTestDetailsAddTaskViewHolder -> holder.bind(addTask)
        }
    }

    override fun onViewRecycled(holder: AdminTestDetailsItemViewHolder) {
        super.onViewRecycled(holder)
        holder.release()
    }
}