package com.flaringapp.testingsimulator.data.network.base

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ErrorResponse(
    val error: ErrorResponseContents? = null
)

@JsonClass(generateAdapter = true)
class ErrorResponseContents(
    @Json(name = "type")
    val errorType: String? = null,
    @Json(name = "description")
    val message: String? = null
)