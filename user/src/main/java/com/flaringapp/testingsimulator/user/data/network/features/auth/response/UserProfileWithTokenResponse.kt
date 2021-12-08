package com.flaringapp.testingsimulator.user.data.network.features.auth.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class UserProfileWithTokenResponse(
    @Json(name = "token")
    val token: String,
    @Json(name = "user")
    val user: UserLoginProfileResponse
)