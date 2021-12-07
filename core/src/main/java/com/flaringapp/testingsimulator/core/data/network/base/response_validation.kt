package com.flaringapp.testingsimulator.core.data.network.base

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.core.data.common.call.CallResultNothing
import com.flaringapp.testingsimulator.core.data.common.call.ErrorType
import com.flaringapp.testingsimulator.core.data.common.call.ErrorType.*
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import okhttp3.ResponseBody
import retrofit2.Response
import java.net.HttpURLConnection.HTTP_NO_CONTENT

private const val STATUS_SUCCESS = "success"

private val errorResponseAdapter: JsonAdapter<ErrorResponse> by lazy {
    Moshi.Builder()
        .build()
        .adapter(ErrorResponse::class.java)
}

fun <T> Response<out ValidateableResponse<List<T>>>.validateList(): CallResult<List<T>> {
    return when {
        !isSuccessful -> null
        code() == HTTP_NO_CONTENT -> CallResult.Success(emptyList())
        else -> body().parseResult {
            if (it == null) CallResult.Success(emptyList())
            else CallResult.Success(it)
        }
    } ?: errorBody().parseErrorResult()
}

fun <T> Response<out ValidateableResponse<T>>.validate(): CallResult<T> {
    return validateAny { CallResult.Success(it!!) }
}

fun Response<BaseResponseSuccess>.validateNoData(): CallResultNothing {
    return validateAny { CallResult.Success(it!!) }
}

private inline fun <T> Response<out ValidateableResponse<out T>>.validateAny(
    collector: (T?) -> CallResult<T>
): CallResult<T> {
    return when {
        !isSuccessful -> null
        code() == HTTP_NO_CONTENT -> noContentErrorResult()
        else -> body().parseResult(collector)
    } ?: errorBody().parseErrorResult()
}

private inline fun <T> ValidateableResponse<out T>?.parseResult(
    collector: (T?) -> CallResult<T>?
): CallResult<T>? {
    return when {
        this == null -> collector(null)
        status != STATUS_SUCCESS -> null
        else -> collector(data)
    }
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
    if (this == null) return CallResult.Error(ApiException(message = null))
    return CallResult.Error(
        exception = ApiException(message),
        errorType = errorType?.parseErrorType()
    )
}

private fun <T> noContentErrorResult() = CallResult.Error<T>(
    exception = ApiException(message = null),
    errorType = ApiResourceNotFound
)

private fun String.parseErrorType(): ErrorType? = when (this) {
    "UNAUTHENTICATED" -> Unauthenticated
    "RESOURCE_NOT_FOUND" -> ApiResourceNotFound
    else -> null
}