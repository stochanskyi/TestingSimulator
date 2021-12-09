package com.flaringapp.testingsimulator.admin.presentation.test_details.adapter

import android.view.ViewGroup
import com.flaringapp.testingsimulator.admin.databinding.ViewHolderAdminTestDetailsAddTaskBinding
import com.flaringapp.testingsimulator.core.app.common.Action
import com.flaringapp.testingsimulator.core.presentation.utils.inflater

class AdminTestDetailsAddTaskViewHolder private constructor(
    private val binding: ViewHolderAdminTestDetailsAddTaskBinding
) : AdminTestDetailsItemViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) = AdminTestDetailsAddTaskViewHolder(
            ViewHolderAdminTestDetailsAddTaskBinding.inflate(parent.inflater, parent, false)
        )
    }

    fun bind(onClick: Action) {
        binding.root.setOnClickListener {
            onClick()
        }
    }

    override fun release() {
        binding.root.setOnClickListener(null)
    }
}