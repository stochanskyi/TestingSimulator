package com.flaringapp.testingsimulator.user.presentation.tests.testDetails.models

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes

data class UserTestStatusViewData(
    val statusName: CharSequence,
    @DrawableRes val statusEmojiRes: Int,
    @ColorInt val statusColor: Int,
    val buttonLabel: String
)