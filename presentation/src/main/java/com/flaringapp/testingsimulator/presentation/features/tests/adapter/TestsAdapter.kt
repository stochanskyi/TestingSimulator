package com.flaringapp.testingsimulator.presentation.features.tests.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.flaringapp.testingsimulator.presentation.features.tests.models.TestViewData

class TestsAdapter(
    private val testClickBlock: (Int) -> Unit
) : ListAdapter<TestViewData, TestViewHolder>(TestsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        return TestViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        holder.bind(getItem(position), testClickBlock)
    }

    override fun onViewRecycled(holder: TestViewHolder) {
        holder.clear()
    }
}