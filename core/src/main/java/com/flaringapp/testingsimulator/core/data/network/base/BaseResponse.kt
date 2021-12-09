package com.flaringapp.testingsimulator.core.data.network.base

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.Response

typealias ApiResponse<T> = Response<BaseResponse<T>>

typealias ApiResponseList<T> = Response<BaseResponse<List<T>>>

@JsonClass(generateAdapter = true)
class BaseResponse<T>(
    override val status: String,
    override val message: String? = null,
    @Json(name = "type")
    override val errorType: String? = null,
    @Json(name = "data")
    override val data: T? = null
) : ValidateableResponse<T>