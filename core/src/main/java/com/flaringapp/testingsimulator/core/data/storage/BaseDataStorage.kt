package com.flaringapp.testingsimulator.core.data.storage

import android.content.Context

abstract class BaseDataStorage(context: Context, name: String) {

    protected val prefs = context.getSharedPreferences(name, Context.MODE_PRIVATE)!!

    operator fun contains(o: Any) = prefs === o

    protected fun <T> preference(name: String, default: T) = Preference(
        prefs = prefs,
        name = name,
        default = default
    )

    protected inline fun <reified T> preferenceNullable(name: String) = PreferenceNullable<T>(
        prefs = prefs,
        name = name,
        clazz = T::class,
    )

}
