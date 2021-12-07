package com.flaringapp.testingsimulator.data.storage.common

import android.content.Context

abstract class Preferences(context: Context, name: String) {

    private val prefs = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    operator fun contains(o: Any) = prefs === o

    internal inner class BoolPref(key: String, defaultValue: Boolean)
        : BoolPrefField(prefs, key, defaultValue)

    internal inner class IntPref(key: String, defaultValue: Int)
        : IntPrefField(prefs, key, defaultValue)

    internal inner class FloatPref(key: String, defaultValue: Float)
        : FloatPrefField(prefs, key, defaultValue)

    internal inner class LongPref(key: String, defaultValue: Long)
        : LongPrefField(prefs, key, defaultValue)

    internal inner class StringPref(key: String, defaultValue: String)
        : StringPrefField(prefs, key, defaultValue)

}
