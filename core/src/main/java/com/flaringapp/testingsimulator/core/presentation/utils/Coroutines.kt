package com.flaringapp.testingsimulator.core.presentation.utils

import androidx.lifecycle.MutableLiveData
import com.flaringapp.testingsimulator.core.app.common.launchOnIO
import com.flaringapp.testingsimulator.core.app.common.withMainContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

fun CoroutineScope.startLoadingTask(
    loadingData: MutableLiveData<Boolean>,
    task: suspend () -> Unit
): Job {
    loadingData.value = true
    return launchOnIO {
        task()
        withMainContext { loadingData.value = false }
    }
}

val Job?.isRunning
    get() = this != null && isActive