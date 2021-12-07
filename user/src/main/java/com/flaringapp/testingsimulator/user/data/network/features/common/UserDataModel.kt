package com.flaringapp.testingsimulator.user.data.network.features.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class UserDataModel(
    @Json(name = "id")
    val id: Int,
    @Json(name = "firstName")
    val firstName: String,
    @Json(name = "lastName")
    val lastName: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "studying")
    val studying: String?,
    @Json(name = "workplace")
    val workplace: String?,
    @Json(name = "role")
    val role: String?,
)