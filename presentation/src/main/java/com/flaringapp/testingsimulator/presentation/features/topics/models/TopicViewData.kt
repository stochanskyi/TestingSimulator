package com.flaringapp.testingsimulator.presentation.features.topics.models

import androidx.annotation.DrawableRes

data class TopicViewData(
    val id: Int,
    val name: String,
    val description: String,
    @DrawableRes val emojiRes: Int,
    val isEnabled: Boolean,
)