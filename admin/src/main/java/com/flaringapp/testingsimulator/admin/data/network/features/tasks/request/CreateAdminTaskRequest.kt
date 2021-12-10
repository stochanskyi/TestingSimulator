package com.flaringapp.testingsimulator.admin.data.network.features.tasks.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CreateAdminTaskRequest(
    @Json(name = "testId")
    val testId: Int,
    @Json(name = "text")
    val name: String,
    @Json(name = "blocks")
    val blocks: List<AddBlockRequest>
)