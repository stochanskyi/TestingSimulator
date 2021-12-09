package com.flaringapp.testingsimulator.presentation.features.tests.adapter

import androidx.recyclerview.widget.DiffUtil
import com.flaringapp.testingsimulator.presentation.features.tests.models.TestViewData

class TestsDiffCallback : DiffUtil.ItemCallback<TestViewData>() {

    override fun areItemsTheSame(oldItem: TestViewData, newItem: TestViewData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TestViewData, newItem: TestViewData): Boolean {
        return oldItem == newItem
    }
}