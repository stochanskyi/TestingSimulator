package com.flaringapp.testingsimulator.user.data.network.features.tasks.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class UserTaskResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "currentOrderNumber")
    val currentOrderNumber: Int,
    @Json(name = "text")
    val name: String,
    @Json(name = "blocks")
    val blocks: List<UserTaskBlockResponse>
)