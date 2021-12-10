package com.flaringapp.testingsimulator.admin.data.network.features.tests.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CreateTestRequest(
    @Json(name = "moduleId")
    val moduleId: Int
)