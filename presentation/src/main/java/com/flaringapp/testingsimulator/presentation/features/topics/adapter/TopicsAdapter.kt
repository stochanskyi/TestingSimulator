package com.flaringapp.testingsimulator.presentation.features.topics.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flaringapp.testingsimulator.presentation.features.topics.models.TopicViewData

class TopicsAdapter(
    private val topicClickBlock: (Int) -> Unit
) : RecyclerView.Adapter<TopicViewHolder>() {

    private var data: List<TopicViewData> = emptyList()

    //TODO diffutils
    fun setData(data: List<TopicViewData>) {
        notifyItemRangeRemoved(0, data.size)
        this.data = data
        notifyItemRangeInserted(0, data.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        return TopicViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        holder.bind(data[position], topicClickBlock)
    }

    override fun getItemCount(): Int = data.size

}