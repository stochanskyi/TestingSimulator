package com.flaringapp.testingsimulator.admin.presentation.test.adapter

import android.view.ViewGroup
import com.flaringapp.testingsimulator.admin.databinding.ViewHolderAdminTestAddTaskBinding
import com.flaringapp.testingsimulator.core.app.common.Action
import com.flaringapp.testingsimulator.core.presentation.utils.inflater

class AdminTestAddTaskViewHolder private constructor(
    private val binding: ViewHolderAdminTestAddTaskBinding
) : AdminTestItemViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) = AdminTestAddTaskViewHolder(
            ViewHolderAdminTestAddTaskBinding.inflate(parent.inflater, parent, false)
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