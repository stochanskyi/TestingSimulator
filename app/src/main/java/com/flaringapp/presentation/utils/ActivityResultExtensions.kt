package com.flaringapp.presentation.utils

import androidx.activity.result.ActivityResultLauncher

infix fun <I> ActivityResultLauncher<I>.startFor(input: I) {
    this.launch(input)
}