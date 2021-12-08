package com.flaringapp.testingsimulator.admin.data.network.features.auth.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class AdminLoginResponse(
    @Json(name = "token")
    val token: String,
    @Json(name = "user")
    val user: AdminLoginProfileResponse
)