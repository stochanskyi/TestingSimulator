package com.flaringapp.testingsimulator.admin.data.network.features.tests.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class AdminTestWithStatisticsModel(
    @Json(name = "test")
    val test: AdminTestDetailsResponse,
    @Json(name = "statistics")
    val statistics: Map<String, String> = emptyMap(),
)