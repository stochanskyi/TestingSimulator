package com.flaringapp.testingsimulator.data.network.base

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.Response

typealias ApiResponseSuccess = Response<BaseResponseSuccess>

@JsonClass(generateAdapter = true)
class BaseResponseSuccess(
    override val status: String,
    override val message: String? = null,
    @Json(name = "type")
    override val errorType: String? = null
) : ValidateableResponse<Unit> {

    override val data = Unit

}