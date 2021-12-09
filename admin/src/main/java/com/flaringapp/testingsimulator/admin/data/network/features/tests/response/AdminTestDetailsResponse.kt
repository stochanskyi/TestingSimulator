package com.flaringapp.testingsimulator.admin.data.network.features.tests.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class AdminTestDetailsResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "tasksCount")
    val tasksCount: Int,
    @Json(name = "status")
    val status: Int,
    @Json(name = "tasks")
    val tasks: List<AdminTaskPreviewResponse> = emptyList(),
)