package com.flaringapp.testingsimulator.core.presentation.utils.livedata

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers

typealias LiveDataList<T> = LiveData<List<T>>
typealias MutableLiveDataList<T> = MutableLiveData<List<T>>
typealias LazyMutableLiveDataList<T> = LazyMutableLiveData<List<T>>
typealias SingleLiveEventList<T> = SingleLiveEvent<List<T>>

fun <T> LiveData<T>.observeOnce(owner: LifecycleOwner, observer: Observer<T>) {
    observe(owner, object : Observer<T> {
        override fun onChanged(t: T) {
            observer.onChanged(t)
            removeObserver(this)
        }
    })
}

fun MutableLiveData<*>.clearValue() {
    if (value != null) {
        value = null
    }
}

fun <T> liveDataIO(
    timeoutInMs: Long = 5000L,
    block: suspend LiveDataScope<T>.() -> Unit
): LiveData<T> = liveData(Dispatchers.IO, timeoutInMs, block)