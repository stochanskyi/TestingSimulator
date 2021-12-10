package com.flaringapp.testingsimulator.user.data.network.features.tests.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class UserTestResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "taskCount")
    val tasksCount: Int,
    @Json(name = "isInProgres")
    val isInProgress: Boolean,
    @Json(name = "taskPassed")
    val tasksPassed: Int,
    @Json(name = "bestScore")
    val bestScore: Int? = null,
)