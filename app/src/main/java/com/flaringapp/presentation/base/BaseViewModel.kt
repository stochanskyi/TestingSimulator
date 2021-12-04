package com.flaringapp.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.flaringapp.app.common.takeIfNotEmpty
import com.flaringapp.data.common.call.CallResult
import com.flaringapp.data.common.call.SafeCallHandler

abstract class BaseViewModel : ViewModel(), BaseViewModelContract, SafeCallHandler {

    override val errorData = MutableLiveData<String>()

    protected suspend fun <T> safeCall(action: suspend () -> CallResult<T>): T? {
        return com.flaringapp.data.common.call.safeCall(this, action)
    }

    override fun <T> handleCallResultError(error: CallResult.Error<T>): Boolean {
        return false
    }

    override fun handleSafeCallError(): Boolean {
        return false
    }

    override fun showError(message: String?) {
        if (message == null || message.isEmpty()) return
        errorData.value = message
    }

}