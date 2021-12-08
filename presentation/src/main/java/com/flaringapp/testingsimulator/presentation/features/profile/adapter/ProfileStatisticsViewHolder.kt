package com.flaringapp.testingsimulator.presentation.features.profile.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flaringapp.testingsimulator.core.presentation.utils.inflater
import com.flaringapp.testingsimulator.presentation.databinding.ViewHolderProfileStatisticsBinding
import com.flaringapp.testingsimulator.presentation.features.profile.models.ProfileStatisticsViewData

class ProfileStatisticsViewHolder(
    private val binding: ViewHolderProfileStatisticsBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) = ProfileStatisticsViewHolder(
            ViewHolderProfileStatisticsBinding.inflate(parent.inflater, parent, false)
        )
    }

    fun bind(item: ProfileStatisticsViewData) = with(binding) {
        root.background.setTint(item.backgroundColor)

        imageEmoji.setImageResource(item.emojiRes)

        textValue.text = item.value
        textValue.setTextColor(item.valueColor)

        textLabel.text = item.label
        textLabel.setTextColor(item.labelColor)
    }

}