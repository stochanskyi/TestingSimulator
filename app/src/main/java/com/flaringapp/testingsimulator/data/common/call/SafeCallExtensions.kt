package com.flaringapp.testingsimulator.data.common.call

suspend fun <D> safeCall(
    handleCallResultError: ((CallResult.Error<*>) -> Boolean)? = null,
    handleSafeCallError: ((Throwable) -> Boolean)? = null,
    showError: ((Throwable?) -> Unit)? = null,
    action: suspend () -> CallResult<D>
): D? {
    return safeCall(
        safeCallHandler(handleCallResultError, handleSafeCallError, showError),
        action
    )
}

fun safeCallHandler(
    handleCallResultError: ((CallResult.Error<*>) -> Boolean)? = null,
    handleSafeCallError: ((Throwable) -> Boolean)? = null,
    showError: ((Throwable?) -> Unit)? = null
) = object : SafeCallHandler {

    override fun <D> handleCallResultError(error: CallResult.Error<D>): Boolean {
        return handleCallResultError?.invoke(error) == true
    }

    override fun handleSafeCallError(error: Throwable): Boolean {
        return handleSafeCallError?.invoke(error) == true
    }

    override fun showError(error: Throwable) {
        showError?.invoke(error)
    }
}