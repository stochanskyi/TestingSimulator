package com.flaringapp.testingsimulator.admin.data.network.features.tasks.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class AdminTaskBlockResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "text")
    val text: String,
    @Json(name = "isEnabled")
    val isEnabled: Boolean,
    @Json(name = "linkedBlockId")
    val linkedBlockId: Int?
)