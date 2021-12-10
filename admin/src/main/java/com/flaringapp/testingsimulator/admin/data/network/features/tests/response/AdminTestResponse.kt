package com.flaringapp.testingsimulator.admin.data.network.features.tests.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class AdminTestResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "taskCount")
    val tasksCount: Int,
    @Json(name = "status")
    val status: Int,
)