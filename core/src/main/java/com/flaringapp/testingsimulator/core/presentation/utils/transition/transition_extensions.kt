package com.flaringapp.testingsimulator.core.presentation.utils.transition

import androidx.transition.Transition
import androidx.transition.TransitionSet

fun List<Transition>.asTransitionSet() = TransitionSet().apply {
    forEach { addTransition(it) }
}