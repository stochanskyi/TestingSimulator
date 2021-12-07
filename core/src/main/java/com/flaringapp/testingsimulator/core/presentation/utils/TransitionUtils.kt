package com.flaringapp.testingsimulator.core.presentation.utils

import android.view.View
import androidx.transition.Transition
import androidx.transition.TransitionSet

operator fun TransitionSet.plusAssign(transition: Transition) {
    addTransition(transition)
}

inline infix fun <reified T : Transition> T.target(target: View): T = addTarget(target) as T
