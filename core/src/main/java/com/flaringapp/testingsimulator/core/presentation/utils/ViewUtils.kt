package com.flaringapp.testingsimulator.core.presentation.utils

import android.content.Context
import android.provider.Settings
import android.view.MenuItem
import android.view.ViewStub
import android.widget.TextView
import androidx.core.view.isVisible

var TextView.textWithVisibility: CharSequence?
    get() = text
    set(value) {
        isVisible = value?.isNotEmpty() == true
        text = value
    }


fun Context.getAnimationDurationScale(): Float {
    return getGlobalSettingsValueF(Settings.Global.ANIMATOR_DURATION_SCALE, 1f)
}

fun Context.getGlobalSettingsValueF(key: String, def: Float): Float {
    return Settings.Global.getFloat(contentResolver, key, def)
}

val ViewStub.isInflated: Boolean
    get() = parent == null

fun ViewStub.safeInflate() {
    if (isInflated) return
    inflate()
}

/**
 * https://stackoverflow.com/questions/6683186/menuitems-checked-state-is-not-shown-correctly-by-its-icon
 */
fun MenuItem.updateCheckedDrawable(
    checkedDrawableRes: Int,
    uncheckedDrawableRes: Int,
) {
    setIcon(
        if (isChecked) checkedDrawableRes
        else uncheckedDrawableRes
    )
}