package com.flaringapp.testingsimulator.presentation.features.profile.adapter

import androidx.recyclerview.widget.DiffUtil
import com.flaringapp.testingsimulator.presentation.features.profile.models.ProfileStatisticsViewData

class ProfileStatisticsDiffCallback : DiffUtil.ItemCallback<ProfileStatisticsViewData>() {

    override fun areItemsTheSame(
        oldItem: ProfileStatisticsViewData,
        newItem: ProfileStatisticsViewData
    ): Boolean {
        return oldItem.emojiRes == newItem.emojiRes &&
            oldItem.backgroundColor == newItem.backgroundColor
    }

    override fun areContentsTheSame(
        oldItem: ProfileStatisticsViewData,
        newItem: ProfileStatisticsViewData
    ): Boolean {
        return oldItem == newItem
    }
}