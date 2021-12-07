package com.flaringapp.testingsimulator.core.presentation.utils

import android.view.Window
import android.view.WindowManager

fun Window.keepScreenOn() {
    addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
}