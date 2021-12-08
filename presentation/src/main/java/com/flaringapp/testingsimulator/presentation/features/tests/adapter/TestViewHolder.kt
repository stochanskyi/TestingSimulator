package com.flaringapp.testingsimulator.presentation.features.tests.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flaringapp.testingsimulator.core.presentation.utils.inflater
import com.flaringapp.testingsimulator.presentation.databinding.ViewHolderTestBinding
import com.flaringapp.testingsimulator.presentation.features.tests.models.TestViewData

class TestViewHolder private constructor(
    private val binding: ViewHolderTestBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) = TestViewHolder(
            ViewHolderTestBinding.inflate(parent.inflater, parent, false)
        )
    }

    fun bind(
        data: TestViewData,
        clickBlock: (Int) -> Unit
    ) = with(binding) {
        titleTextView.text = data.name
        descriptionTextView.text = data.description

        statusTextView.text = data.status
        statusTextView.background.setTint(data.statusColor)

        root.setOnClickListener { clickBlock(data.id) }
    }

    fun clear() {
        binding.root.setOnClickListener(null)
    }
}