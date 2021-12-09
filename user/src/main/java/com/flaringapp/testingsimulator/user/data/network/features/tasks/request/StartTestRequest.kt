package com.flaringapp.testingsimulator.user.data.network.features.tasks.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class StartTestRequest(
    @Json(name = "testId")
    val testId: Int
)