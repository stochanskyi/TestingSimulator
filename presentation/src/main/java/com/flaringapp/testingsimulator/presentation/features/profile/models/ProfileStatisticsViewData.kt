package com.flaringapp.testingsimulator.presentation.features.profile.models

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes

data class ProfileStatisticsViewData(
    @DrawableRes
    val emojiRes: Int,
    val value: CharSequence,
    val label: CharSequence,
    @ColorInt
    val backgroundColor: Int,
    @ColorInt
    val valueColor: Int,
    @ColorInt
    val labelColor: Int,
)