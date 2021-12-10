package com.flaringapp.testingsimulator.admin.data.network.features.tasks.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class EditTaskRequest(
    @Json(name = "taskId")
    val testId: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "blocks")
    val blocks: List<AddBlockRequest>
)