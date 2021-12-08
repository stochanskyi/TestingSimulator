package com.flaringapp.testingsimulator.data.network.features.edit_profile.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class EditProfileRequest(
    @Json(name = "firstName")
    val firstName: String,
    @Json(name = "lastName")
    val lastName: String,
    @Json(name = "studying")
    val studying: String? = null,
    @Json(name = "workPlace")
    val workPlace: String? = null,
    @Json(name = "role")
    val role: String? = null,
)