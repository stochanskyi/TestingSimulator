package com.flaringapp.testingsimulator.presentation.utils.debounce

import kotlin.reflect.KProperty

object NavigationDebouncer {

    const val DEBOUNCE_DURATION = 250L

    operator fun invoke() = TimeDebouncer(DEBOUNCE_DURATION)

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Debouncer {
        return this()
    }

}