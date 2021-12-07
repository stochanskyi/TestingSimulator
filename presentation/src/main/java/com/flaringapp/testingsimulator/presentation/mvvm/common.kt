package com.flaringapp.testingsimulator.presentation.mvvm

import androidx.fragment.app.Fragment

inline fun <reified T> Fragment.parentAsListener(): T {
    return parentAsListenerOrNull()
        ?: throw IllegalStateException("Parent of type ${T::class} not found")
}

inline fun <reified T> Fragment.parentAsListenerOrNull(): T? {
    return parentFragment as? T
        ?: parentFragment?.parentFragment as? T
        ?: activity as? T
}