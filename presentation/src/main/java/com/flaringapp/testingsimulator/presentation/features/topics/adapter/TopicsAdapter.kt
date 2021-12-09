package com.flaringapp.testingsimulator.presentation.features.topics.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.flaringapp.testingsimulator.presentation.features.topics.models.TopicViewData

class TopicsAdapter(
    private val topicClickBlock: (Int) -> Unit
) : ListAdapter<TopicViewData, TopicViewHolder>(TopicsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        return TopicViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        holder.bind(getItem(position), topicClickBlock)
    }

    override fun onViewRecycled(holder: TopicViewHolder) {
        holder.clear()
        super.onViewRecycled(holder)
    }

}