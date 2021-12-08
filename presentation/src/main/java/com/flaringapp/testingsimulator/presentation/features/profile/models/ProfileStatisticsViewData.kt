package com.flaringapp.testingsimulator.presentation.features.profile.models

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes

class ProfileStatisticsViewData(
    @DrawableRes
    val iconRes: Int,
    val value: CharSequence,
    val label: CharSequence,
    @ColorInt
    val backgroundColor: Int,
    @ColorInt
    val valueColor: Int,
)