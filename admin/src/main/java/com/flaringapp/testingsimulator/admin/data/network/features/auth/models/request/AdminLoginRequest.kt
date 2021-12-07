package com.flaringapp.testingsimulator.admin.data.network.features.auth.models.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class AdminLoginRequest(
    @Json(name = "email")
    val email: String,
    @Json(name = "password")
    val password: String,
    @Json(name = "isAdmin")
    val isAdmin: Boolean = true
)