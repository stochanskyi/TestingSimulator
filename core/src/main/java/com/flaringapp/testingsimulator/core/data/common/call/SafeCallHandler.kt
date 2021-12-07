package com.flaringapp.testingsimulator.core.data.common.call

interface SafeCallHandler {

    fun <D> handleCallResultError(error: CallResult.Error<D>): Boolean

    fun handleSafeCallError(error: Throwable): Boolean

    fun showError(error: Throwable)

}