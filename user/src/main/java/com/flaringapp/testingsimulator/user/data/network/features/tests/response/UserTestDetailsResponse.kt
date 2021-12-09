package com.flaringapp.testingsimulator.user.data.network.features.tests.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class UserTestDetailsResponse(
    @Json(name = "test")
    val test: UserTestResponse,
    @Json(name = "statistics")
    val statistics: Map<String, String> = emptyMap()
)