package com.flaringapp.testingsimulator.user.data.network.features.tasks.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class UserTaskAnswerRequest(
    @Json(name = "taskId")
    val taskId: Int,
    @Json(name = "blocks")
    val blocks: List<Int>
)