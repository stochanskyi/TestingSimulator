package com.flaringapp.testingsimulator.user.data.network.features.tasks.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class UserTaskBlockResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "text")
    val text: String
)