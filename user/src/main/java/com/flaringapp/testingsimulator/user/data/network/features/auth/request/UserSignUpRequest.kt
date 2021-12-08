package com.flaringapp.testingsimulator.user.data.network.features.auth.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class UserSignUpRequest(
    @Json(name = "email")
    val email: String,
    @Json(name = "firstName")
    val firstName: String,
    @Json(name = "lastName")
    val lastName: String,
    @Json(name = "studying")
    val studying: String?,
    @Json(name = "workplace")
    val workPlace: String?,
    @Json(name = "role")
    val role: String?,
    @Json(name = "password")
    val password: String,
    @Json(name = "passwordRepeat")
    val passwordRepeat: String,
    @Json(name = "isAdmin")
    val isAdmin: Boolean = false,
)