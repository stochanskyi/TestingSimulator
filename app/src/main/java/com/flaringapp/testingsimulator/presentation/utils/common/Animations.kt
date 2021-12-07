package com.flaringapp.testingsimulator.presentation.utils.common

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.transition.Fade
import androidx.transition.TransitionManager
import com.flaringapp.testingsimulator.presentation.utils.target

fun ViewGroup.animateProgressBar(progressBar: View, isVisible: Boolean) {
    if (progressBar.isVisible == isVisible) return

    val transition = Fade().apply {
        duration = 150
        interpolator = FastOutSlowInInterpolator()
    } target progressBar
    TransitionManager.beginDelayedTransition(this, transition)
}

var View.isVisibleAndAnimateProgressBar
    get() = isVisible
    set(value) {
        (parent as ViewGroup).animateProgressBar(this, isVisible)
        isVisible = value
    }