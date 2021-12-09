package com.flaringapp.testingsimulator.presentation.features.topics.adapter

import androidx.recyclerview.widget.DiffUtil
import com.flaringapp.testingsimulator.presentation.features.topics.models.TopicViewData

class TopicsDiffCallback : DiffUtil.ItemCallback<TopicViewData>() {

    override fun areItemsTheSame(oldItem: TopicViewData, newItem: TopicViewData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TopicViewData, newItem: TopicViewData): Boolean {
        return oldItem == newItem
    }
}