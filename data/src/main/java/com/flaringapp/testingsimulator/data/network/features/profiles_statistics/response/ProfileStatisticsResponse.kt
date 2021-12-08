package com.flaringapp.testingsimulator.data.network.features.profiles_statistics.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ProfileStatisticsResponse(
    @Json(name = "value")
    val value: String,
    @Json(name = "label")
    val label: String,
    @Json(name = "emojiId")
    val emojiId: Int,
)