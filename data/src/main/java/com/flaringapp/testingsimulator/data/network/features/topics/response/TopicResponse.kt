package com.flaringapp.testingsimulator.data.network.features.topics.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class TopicResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "enabled")
    val enabled: Boolean,
    @Json(name = "emojiId")
    val emojiId: Int,
)