package com.flaringapp.testingsimulator.user.data.network.features.auth.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class UserLoginRequest(
    @Json(name = "email")
    val email: String,
    @Json(name = "password")
    val password: String,
    @Json(name = "isAdmin")
    val isAdmin: Boolean = false
)