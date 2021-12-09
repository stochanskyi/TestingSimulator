package com.flaringapp.testingsimulator.core.data.network.base

import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.core.data.common.call.CallResultNothing
import com.flaringapp.testingsimulator.core.data.common.call.ErrorType
import com.flaringapp.testingsimulator.core.data.common.call.ErrorType.ApiResourceNotFound
import com.flaringapp.testingsimulator.core.data.common.call.ErrorType.Unauthenticated
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import okhttp3.ResponseBody
import retrofit2.Response
import java.net.HttpURLConnection.HTTP_NO_CONTENT

private const val STATUS_SUCCESS = "OK"

private val errorResponseAdapter: JsonAdapter<ErrorResponse> by lazy {
    Moshi.Builder()
        .build()
        .adapter(ErrorResponse::class.java)
}

fun <T> Response<out ValidateableResponse<List<T>>>.validateList(): CallResult<List<T>> {
    return when {
        !isSuccessful -> null
        code() == HTTP_NO_CONTENT -> CallResult.Success(emptyList())
        else -> body().parseBody {
            if (it == null) CallResult.Success(emptyList())
            else CallResult.Success(it)
        }
    } ?: classifyError()
}

fun <T> Response<out ValidateableResponse<T>>.validate(): CallResult<T> {
    return validateAny { data ->
        if (data == null) return@validateAny null
        CallResult.Success(data)
    }
}

fun Response<BaseResponseSuccess>.validateNoData(): CallResultNothing {
    return validateAny {
        CallResult.Success(it)
    }
}

private inline fun <T> Response<out ValidateableResponse<out T>>.validateAny(
    collector: (T?) -> CallResult<T>?
): CallResult<T> {
    return when {
        !isSuccessful -> null
        code() == HTTP_NO_CONTENT -> noContentErrorResult()
        else -> body().parseBody(collector)
    } ?: classifyError()
}

private inline fun <T> ValidateableResponse<out T>?.parseBody(
    collector: (T?) -> CallResult<T>?
): CallResult<T>? {
    return when {
        this == null -> collector(null)
        status != STATUS_SUCCESS -> parseBodyError()
        else -> collector(data)
    }
}

private fun <T> ValidateableResponse<out T>.parseBodyError(): CallResult.Error<T> {
    return CallResult.Error(
        exception = ApiException(message),
        errorType = errorType?.parseErrorType(),
    )
}

private fun <T> Response<out ValidateableResponse<out T>>.classifyError(): CallResult.Error<T> {
    return errorBody()?.parseErrorBody() ?: unknownError()
}

private fun <T> ResponseBody.parseErrorBody(): CallResult.Error<T>? {
    return convertErrorBody()?.toCallResult()
}

private fun ResponseBody.convertErrorBody(): ErrorResponseContents? {
    return try {
        val source = source()
        errorResponseAdapter.fromJson(source)?.error
    } catch (exception: Exception) {
        null
    }
}

private fun <T> ErrorResponseContents.toCallResult(): CallResult.Error<T> {
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

private fun <T> unknownError() = CallResult.Error<T>(ApiException(message = null))