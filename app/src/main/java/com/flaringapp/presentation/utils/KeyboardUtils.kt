package com.flaringapp.presentation.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

object KeyboardUtils {

    fun hideKeyboard(context: Context?, view: View?) {
        if (context == null || view == null) return
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}

fun Fragment.hideKeyboard() = activity?.hideKeyboard()

fun Activity.hideKeyboard() = KeyboardUtils.hideKeyboard(this, currentFocus)