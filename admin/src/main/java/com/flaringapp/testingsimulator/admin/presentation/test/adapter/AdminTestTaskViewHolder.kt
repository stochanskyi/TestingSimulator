package com.flaringapp.testingsimulator.admin.presentation.test.adapter

import android.view.ViewGroup
import com.flaringapp.testingsimulator.admin.databinding.ViewHolderAdminTestTaskBinding
import com.flaringapp.testingsimulator.admin.presentation.test.models.AdminTestBlockViewData
import com.flaringapp.testingsimulator.core.presentation.utils.inflater

class AdminTestTaskViewHolder private constructor(
    private val binding: ViewHolderAdminTestTaskBinding
): AdminTestItemViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) = AdminTestTaskViewHolder(
            ViewHolderAdminTestTaskBinding.inflate(parent.inflater, parent, false)
        )
    }

    fun bind(item: AdminTestBlockViewData) = with(binding) {
        root.text = item.text
    }
}