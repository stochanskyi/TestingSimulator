package com.flaringapp.testingsimulator.core.presentation.utils.debounce

import android.view.View

fun View.setOnClickListenerDebounced(debouncer: Debouncer, listener: View.OnClickListener?) {
    if (listener == null) {
        this.setOnClickListener(null)
        return
    }
    this.setOnClickListener innerListener@{
        if (debouncer.debounce()) return@innerListener
        listener.onClick(it)
    }
}