package com.flaringapp.data.common.call

import com.flaringapp.app.common.withMainContext

suspend fun <T> safeCall(handler: SafeCallHandler, action: suspend () -> CallResult<T>): T? {
    return try {
        val result = action()
        processRequestResult(handler, result)
    } catch (e: Exception) {
        val isErrorHandled = withMainContext {
            handler.handleSafeCallError()
        }
        if (isErrorHandled) return null
        e.printStackTrace()
        handler.showErrorOnMain(e.message)
        null
    }
}

private suspend fun <T> processRequestResult(handler: SafeCallHandler, result: CallResult<T>): T? {
    if (result is CallResult.Error) {
        val isErrorHandled = withMainContext {
            handler.handleCallResultError(result)
        }
        if (isErrorHandled) return null
        handler.showErrorOnMain(result.message)
        return null
    }

    return (result as CallResult.Success<T>).data
}

private suspend fun SafeCallHandler.showErrorOnMain(message: String?) {
    withMainContext {
        showError(message)
    }
}