package com.flaringapp.testingsimulator.core.presentation.utils.livedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

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