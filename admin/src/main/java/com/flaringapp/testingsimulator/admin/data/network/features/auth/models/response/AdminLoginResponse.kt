package com.flaringapp.testingsimulator.admin.data.network.features.auth.models.response

import com.flaringapp.testingsimulator.admin.data.network.features.common.AdminDataModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class AdminLoginResponse(
    @Json(name = "token")
    val token: String,
    @Json(name = "user")
    val user: AdminDataModel
)