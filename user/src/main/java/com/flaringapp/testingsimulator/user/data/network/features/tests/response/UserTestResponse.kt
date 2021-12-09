package com.flaringapp.testingsimulator.user.data.network.features.tests.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class UserTestResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "tasksCount")
    val tasksCount: Int,
    @Json(name = "isInProgress")
    val isInProgress: Boolean,
    @Json(name = "bestScore")
    val bestScore: Int,
)