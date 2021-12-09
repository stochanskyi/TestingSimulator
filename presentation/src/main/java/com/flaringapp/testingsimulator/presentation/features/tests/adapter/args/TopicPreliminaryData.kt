package com.flaringapp.testingsimulator.presentation.features.tests.adapter.args

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class TopicPreliminaryData(
    val id: Int,
    val name: String
): Parcelable