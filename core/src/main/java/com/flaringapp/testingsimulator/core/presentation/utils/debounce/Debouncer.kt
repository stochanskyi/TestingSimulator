package com.flaringapp.testingsimulator.core.presentation.utils.debounce

interface Debouncer {

    fun debounce(): Boolean

    fun reset()

}