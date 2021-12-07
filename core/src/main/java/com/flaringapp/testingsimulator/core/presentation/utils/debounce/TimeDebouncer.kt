package com.flaringapp.testingsimulator.core.presentation.utils.debounce

import android.os.SystemClock

open class TimeDebouncer(
    private val debounceTimeMillis: Long,
) : Debouncer {

    private var lastDebounce = 0L

    override fun debounce(): Boolean {
        val currentDebounce = SystemClock.elapsedRealtime()
        if (lastDebounce + debounceTimeMillis > currentDebounce) return true

        lastDebounce = currentDebounce
        return false
    }

    override fun reset() {
        lastDebounce = 0
    }
}