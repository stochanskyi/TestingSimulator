package com.flaringapp.presentation.base

import androidx.lifecycle.ViewModel
import com.flaringapp.data.common.call.CallResult
import com.flaringapp.data.common.call.SafeCallHandler
import com.flaringapp.presentation.utils.common.SingleLiveEvent

abstract class BaseViewModel : ViewModel(), BaseViewModelContract, SafeCallHandler {

    override val errorData = SingleLiveEvent<Throwable>()

    protected suspend fun <D> safeCall(action: suspend () -> CallResult<D>): D? {
        return com.flaringapp.data.common.call.safeCall(this, action)
    }

    override fun <D> handleCallResultError(error: CallResult.Error<D>): Boolean {
        return false
    }

    override fun handleSafeCallError(error: Throwable): Boolean {
        return false
    }

    override fun showError(error: Throwable) {
        errorData.value = error
    }

}