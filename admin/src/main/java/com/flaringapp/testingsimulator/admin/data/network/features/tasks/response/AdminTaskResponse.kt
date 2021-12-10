package com.flaringapp.testingsimulator.admin.data.network.features.tasks.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class AdminTaskResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "text")
    val name: String,
    @Json(name = "difficultyLevel")
    val difficultyLevel: Int,
    @Json(name = "blocks")
    val blocks: List<AdminTaskBlockResponse>
)