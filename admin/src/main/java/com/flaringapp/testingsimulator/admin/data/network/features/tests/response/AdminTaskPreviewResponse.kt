package com.flaringapp.testingsimulator.admin.data.network.features.tests.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class AdminTaskPreviewResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "text")
    val text: String,
    @Json(name = "difficultyLevel")
    val difficultyLevel: Int,
)