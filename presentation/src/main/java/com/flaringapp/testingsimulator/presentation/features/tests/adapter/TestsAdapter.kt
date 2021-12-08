package com.flaringapp.testingsimulator.presentation.features.tests.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flaringapp.testingsimulator.presentation.features.tests.models.TestViewData

class TestsAdapter(
    private val testClickBlock: (Int) -> Unit
) : RecyclerView.Adapter<TestViewHolder>() {

    private var data: List<TestViewData> = emptyList()

    fun setData(data: List<TestViewData>) {
        notifyItemRangeRemoved(0, this.data.size)
        this.data = data
        notifyItemRangeInserted(0, this.data.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        return TestViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        holder.bind(data[position], testClickBlock)
    }

    override fun onViewRecycled(holder: TestViewHolder) {
        holder.clear()
    }

    override fun getItemCount(): Int = data.size
}