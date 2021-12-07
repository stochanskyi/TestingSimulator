package com.flaringapp.testingsimulator.presentation.utils.debounce

interface Debouncer {

    fun debounce(): Boolean

    fun reset()

}