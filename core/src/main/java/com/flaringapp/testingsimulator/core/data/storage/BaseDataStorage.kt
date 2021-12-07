package com.flaringapp.testingsimulator.core.data.storage

import android.content.Context

abstract class BaseDataStorage(context: Context, name: String) {

    private val prefs = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    operator fun contains(o: Any) = prefs === o

    protected fun <T> preference(name: String, default: T) = Preference(
        prefs = prefs,
        name = name,
        default = default
    )

    protected fun <T> preferenceNullable(name: String, default: T? = null) = PreferenceNullable(
        prefs = prefs,
        name = name,
        default = default,
    )

}
