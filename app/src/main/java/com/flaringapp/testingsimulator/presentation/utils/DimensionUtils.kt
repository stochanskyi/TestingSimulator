package com.flaringapp.testingsimulator.presentation.utils

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue

fun Context.dpi(dp: Float): Int {
    return resources.dp(dp).toInt()
}

fun Context.dpi(dp: Int): Int {
    return resources.dp(dp).toInt()
}

fun Context.dp(dp: Float): Float {
    return resources.dp(dp)
}

fun Context.dp(dp: Int): Float {
    return resources.dp(dp)
}

fun Context.spi(sp: Float): Int {
    return resources.sp(sp).toInt()
}

fun Context.spi(sp: Int): Int {
    return resources.sp(sp).toInt()
}

fun Context.sp(sp: Float): Float {
    return resources.sp(sp)
}

fun Context.sp(sp: Int): Float {
    return resources.sp(sp)
}

fun Resources.dp(dp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics)
}

fun Resources.dp(dp: Int): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), displayMetrics)
}

fun Resources.sp(sp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, displayMetrics)
}

fun Resources.sp(sp: Int): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp.toFloat(), displayMetrics)
}