package com.flaringapp.testingsimulator.admin.data.network.features.tasks.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class AddBlockRequest(
    @Json(name = "text")
    val text: String,
    @Json(name = "isEnabled")
    val isEnabled: Boolean,
    @Json(name = "linkedBlockPosition")
    val linkedBlockPosition: Int?,
)