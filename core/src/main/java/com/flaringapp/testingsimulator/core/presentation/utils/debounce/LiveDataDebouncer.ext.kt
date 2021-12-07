package com.flaringapp.testingsimulator.core.presentation.utils.debounce

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeDebounced(
    lifecycleOwner: LifecycleOwner,
    debounceTimeMillis: Long,
    observer: Observer<in T>
) {
    val debouncer = TimeDebouncer(debounceTimeMillis)
    observeDebounced(lifecycleOwner, debouncer, observer)
}

fun <T> LiveData<T>.observeDebounced(
    lifecycleOwner: LifecycleOwner,
    debouncer: Debouncer,
    observer: Observer<in T>
) {
    observe(lifecycleOwner) internalObserve@{ value ->
        if (debouncer.debounce()) return@internalObserve
        observer.onChanged(value)
    }
}
