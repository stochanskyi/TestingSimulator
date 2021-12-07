package com.flaringapp.testingsimulator.admin.data.network.features.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class AdminDataModel(
    @Json(name = "id")
    val id: Int,
    @Json(name = "firstName")
    val firstName: String,
    @Json(name = "lastName")
    val lastName: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "workplace")
    val workplace: String?,
    @Json(name = "role")
    val role: String?,
)