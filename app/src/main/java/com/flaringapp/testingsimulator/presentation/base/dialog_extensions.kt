package com.flaringapp.testingsimulator.presentation.base

import android.view.View
import androidx.fragment.app.DialogFragment
import com.flaringapp.testingsimulator.R

fun DialogFragment.setupRoundedBackground(root: View) {
    root.setBackgroundResource(R.drawable.bg_dialog)
    root.clipToOutline = true
}