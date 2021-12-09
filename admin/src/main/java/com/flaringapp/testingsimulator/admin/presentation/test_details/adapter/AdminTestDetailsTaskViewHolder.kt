package com.flaringapp.testingsimulator.admin.presentation.test_details.adapter

import android.view.ViewGroup
import com.flaringapp.testingsimulator.admin.databinding.ViewHolderAdminTestDetailsTaskBinding
import com.flaringapp.testingsimulator.admin.presentation.test_details.models.AdminTestDetailsTaskViewData
import com.flaringapp.testingsimulator.core.presentation.utils.inflater

class AdminTestDetailsTaskViewHolder private constructor(
    private val binding: ViewHolderAdminTestDetailsTaskBinding
): AdminTestDetailsItemViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) = AdminTestDetailsTaskViewHolder(
            ViewHolderAdminTestDetailsTaskBinding.inflate(parent.inflater, parent, false)
        )
    }

    fun bind(
        item: AdminTestDetailsTaskViewData,
        onClick: (Int) -> Unit,
    ) = with(binding) {
        root.text = item.text
        root.setOnClickListener { onClick(item.id) }
    }
}