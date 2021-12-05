package com.flaringapp.presentation.utils.debounce

interface Debouncer {

    fun debounce(): Boolean

    fun reset()

}