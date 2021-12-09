package com.flaringapp.testingsimulator.presentation.mvvm

import androidx.lifecycle.ViewModel
import com.flaringapp.testingsimulator.core.data.common.call.CallResult
import com.flaringapp.testingsimulator.core.data.common.call.SafeCallHandler
import com.flaringapp.testingsimulator.core.data.common.call.safeCall
import com.flaringapp.testingsimulator.core.presentation.utils.livedata.SingleLiveEvent

abstract class BaseViewModel : ViewModel(), BaseViewModelContract, SafeCallHandler {

    override val errorData = SingleLiveEvent<Throwable?>()

    protected suspend fun <D> safeCall(action: suspend () -> CallResult<D>): D? {
        return safeCall(this, action)
    }

    override fun <D> handleCallResultError(error: CallResult.Error<D>): Boolean {
        return false
    }

    override fun handleSafeCallError(error: Throwable): Boolean {
        return false
    }

    override fun showError(error: Throwable?) {
        errorData.value = error
    }

}