package com.flaringapp.presentation.utils.debounce

class SynchronizedTimeDebouncer(
    debounceTimeMillis: Long,
) : TimeDebouncer(debounceTimeMillis) {

    private val lock = Object()

    override fun debounce(): Boolean = synchronized(lock) {
        super.debounce()
    }

    override fun reset() = synchronized(lock) {
        super.reset()
    }
}