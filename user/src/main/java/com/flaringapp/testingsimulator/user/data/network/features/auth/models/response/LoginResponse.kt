package com.flaringapp.testingsimulator.user.data.network.features.auth.models.response

import com.flaringapp.testingsimulator.user.data.network.features.common.UserDataModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.nio.file.attribute.UserDefinedFileAttributeView

@JsonClass(generateAdapter = true)
class LoginResponse(
    @Json(name = "token")
    val token: String,
    @Json(name = "user")
    val user: UserDataModel
)