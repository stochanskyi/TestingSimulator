package com.flaringapp.testingsimulator.admin.data.network.features.tests.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ChangeTestStateRequest(
    @Json(name = "id")
    val testId: Int,
    @Json(name = "state")
    val state: Int,
)