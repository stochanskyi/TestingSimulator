package com.flaringapp.testingsimulator.presentation.features.topics.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flaringapp.testingsimulator.core.presentation.utils.inflater
import com.flaringapp.testingsimulator.core.presentation.utils.setAlphaTraversable
import com.flaringapp.testingsimulator.presentation.databinding.ViewHolderTopicBinding
import com.flaringapp.testingsimulator.presentation.features.topics.models.TopicViewData

class TopicViewHolder private constructor(
    private val binding: ViewHolderTopicBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        private const val TOPIC_DISABLED_ALPHA = 0.5f
        private const val TOPIC_ENABLED_ALPHA = 1f

        fun create(parent: ViewGroup) = TopicViewHolder(
            ViewHolderTopicBinding.inflate(parent.inflater, parent, false)
        )
    }

    fun bind(
        data: TopicViewData,
        clickBlock: (Int) -> Unit
    ) = with(binding) {
        binding.root.isEnabled = data.isEnabled
        binding.root.setAlphaTraversable(getAlpha(data.isEnabled))
        binding.root.setOnClickListener { clickBlock(data.id) }

        emojiImageView.setImageResource(data.emojiRes)

        titleTextView.text = data.name

        descriptionTextView.text = data.description
    }

    fun clear() {
        binding.root.setOnClickListener(null)
    }

    private fun getAlpha(isEnabled: Boolean): Float {
        return if (isEnabled) TOPIC_ENABLED_ALPHA
        else TOPIC_DISABLED_ALPHA
    }

}