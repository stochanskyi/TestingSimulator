package com.flaringapp.data.network.base

import com.flaringapp.data.common.call.CallResult
import com.flaringapp.data.common.call.CallResultNothing
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import okhttp3.ResponseBody
import retrofit2.Response

private val errorResponseAdapter: JsonAdapter<ErrorResponse> by lazy {
    Moshi.Builder()
        .build()
        .adapter(ErrorResponse::class.java)
}

fun <T> Response<out ValidateableResponse<List<T>>>.validateList(): CallResult<List<T>> {
    return if (isSuccessful) {
        body().parseResult {
            if (it == null) CallResult.Success(emptyList())
            else CallResult.Success(it)
        }
    } else errorBody().parseErrorResult()
}

fun <T> Response<out ValidateableResponse<T>>.validate(): CallResult<T> {
    return validateAny { CallResult.Success(it!!) }
}

fun Response<BaseResponseSuccess>.validateNoData(): CallResultNothing {
    return validateAny { CallResult.Success(it!!) }
}

private fun <T> Response<out ValidateableResponse<out T>>.validateAny(
    collector: (T?) -> CallResult<T>
): CallResult<T> {
    return if (isSuccessful) body().parseResult(collector)
    else errorBody().parseErrorResult()
}

private fun <T> ValidateableResponse<out T>?.parseResult(
    collector: (T?) -> CallResult<T>
): CallResult<T> {
    if (this == null) return CallResult.UnknownError()
    return if (status == "success") collector(data)
    else CallResult.Error(errorType, message ?: "")
}

private fun <T> ResponseBody?.parseErrorResult(): CallResult<T> {
    if (this == null) return CallResult.UnknownError()
    return convertErrorBody().toCallResult()
}

private fun ResponseBody.convertErrorBody(): ErrorResponseContents? {
    return try {
        val source = source()
        errorResponseAdapter.fromJson(source)?.error
    } catch (exception: Exception) {
        null
    }
}

private fun <T> ErrorResponseContents?.toCallResult(): CallResult<T> {
    if (this == null) return CallResult.UnknownError()
    return CallResult.Error(errorType, message ?: "")
}