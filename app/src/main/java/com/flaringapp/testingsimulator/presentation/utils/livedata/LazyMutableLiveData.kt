package com.flaringapp.testingsimulator.presentation.utils.livedata

import androidx.annotation.MainThread
import androidx.annotation.Nullable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class LazyMutableLiveData<T>(
    private val initialValue: () -> T
) : MutableLiveData<T>() {

    private var isInitialValuePending = true

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        checkAndSetPendingValue()
        super.observe(owner, observer)
    }

    override fun observeForever(observer: Observer<in T>) {
        checkAndSetPendingValue()
        super.observeForever(observer)
    }

    @MainThread
    override fun setValue(@Nullable value: T?) {
        isInitialValuePending = false
        super.setValue(value)
    }

    private fun checkAndSetPendingValue() {
        if (isInitialValuePending) {
            value = initialValue()
        }
    }

}