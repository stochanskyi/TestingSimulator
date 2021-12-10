package com.flaringapp.testingsimulator.admin.data.network.features.tasks.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class EditAdminTaskRequest(
    @Json(name = "taskId")
    val taskId: Int,
    @Json(name = "text")
    val name: String,
    @Json(name = "blocks")
    val blocks: List<AddBlockRequest>
)