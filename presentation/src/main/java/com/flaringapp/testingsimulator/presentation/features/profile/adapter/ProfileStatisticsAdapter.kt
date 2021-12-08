package com.flaringapp.testingsimulator.presentation.features.profile.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.flaringapp.testingsimulator.presentation.features.profile.models.ProfileStatisticsViewData


class ProfileStatisticsAdapter : ListAdapter<
    ProfileStatisticsViewData,
    ProfileStatisticsViewHolder>(
    ProfileStatisticsDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileStatisticsViewHolder {
        return ProfileStatisticsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ProfileStatisticsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}