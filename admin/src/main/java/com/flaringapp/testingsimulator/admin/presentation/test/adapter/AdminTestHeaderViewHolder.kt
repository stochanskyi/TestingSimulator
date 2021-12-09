package com.flaringapp.testingsimulator.admin.presentation.test.adapter

import android.view.ViewGroup
import com.flaringapp.testingsimulator.admin.databinding.ViewHolderAdminTestHeaderBinding
import com.flaringapp.testingsimulator.admin.presentation.test.models.AdminTestHeaderViewData
import com.flaringapp.testingsimulator.core.presentation.utils.inflater

class AdminTestHeaderViewHolder private constructor(
    private val binding: ViewHolderAdminTestHeaderBinding
): AdminTestItemViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) = AdminTestHeaderViewHolder(
            ViewHolderAdminTestHeaderBinding.inflate(parent.inflater, parent, false)
        )
    }

    fun bind(item: AdminTestHeaderViewData) = with(binding) {
        textName.text = item.name
        textStatistics.text = item.statusAndStatistics
    }
}