package com.flaringapp.testingsimulator.admin.presentation.task_view.adapter

import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.flaringapp.testingsimulator.admin.databinding.ViewHolderAdminTaskViewBlockBinding
import com.flaringapp.testingsimulator.admin.presentation.task_view.models.AdminViewTaskBlockViewData
import com.flaringapp.testingsimulator.core.presentation.utils.inflater

class AdminViewTaskBlockViewHolder private constructor(
    private val binding: ViewHolderAdminTaskViewBlockBinding
): RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) = AdminViewTaskBlockViewHolder(
            ViewHolderAdminTaskViewBlockBinding.inflate(parent.inflater, parent, false)
        )
    }

    fun bind(item: AdminViewTaskBlockViewData) = with(binding) {
        root.isSelected = item.isEnabled

        textBlock.text = item.text

        imageLink.isVisible = item.isLinked
    }
}