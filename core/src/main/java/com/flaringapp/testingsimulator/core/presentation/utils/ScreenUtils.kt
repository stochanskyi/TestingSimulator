package com.flaringapp.testingsimulator.core.presentation.utils

import android.app.Activity
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.Fragment

fun Window.keepScreenOn() {
    addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
}

fun Fragment.hideKeyboardAndClearCurrentFocus() {
    activity?.hideKeyboardAndClearCurrentFocus()
}

fun Activity.hideKeyboardAndClearCurrentFocus() {
    hideKeyboard()
    clearCurrentFocus()
}

fun Fragment.clearCurrentFocus() {
    activity?.clearCurrentFocus()
}

fun Activity.clearCurrentFocus() {
    val focusedView = currentFocus ?: return
    focusedView.clearFocus()
}