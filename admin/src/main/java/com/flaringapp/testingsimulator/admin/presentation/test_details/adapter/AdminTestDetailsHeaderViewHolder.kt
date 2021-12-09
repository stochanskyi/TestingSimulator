package com.flaringapp.testingsimulator.admin.presentation.test_details.adapter

import android.view.ViewGroup
import com.flaringapp.testingsimulator.admin.databinding.ViewHolderAdminTestDetailsHeaderBinding
import com.flaringapp.testingsimulator.admin.presentation.test_details.models.AdminTestDetailsHeaderViewData
import com.flaringapp.testingsimulator.core.presentation.utils.inflater

class AdminTestDetailsHeaderViewHolder private constructor(
    private val binding: ViewHolderAdminTestDetailsHeaderBinding
): AdminTestDetailsItemViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) = AdminTestDetailsHeaderViewHolder(
            ViewHolderAdminTestDetailsHeaderBinding.inflate(parent.inflater, parent, false)
        )
    }

    fun bind(item: AdminTestDetailsHeaderViewData) = with(binding) {
        textName.text = item.name
        textStatistics.text = item.statusAndStatistics
    }
}