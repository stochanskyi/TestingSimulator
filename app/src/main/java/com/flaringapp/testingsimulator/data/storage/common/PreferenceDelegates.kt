@file:Suppress("NOTHING_TO_INLINE")

package com.flaringapp.testingsimulator.data.storage.common

import android.content.SharedPreferences
import kotlin.reflect.KProperty

internal abstract class BoolPrefField(
    private val prefs: SharedPreferences,
    private val key: String,
    defaultValue: Boolean
) {

    private var cachedValue = prefs.getBoolean(key, defaultValue)

    inline operator fun getValue(thisRef: Any?, prop: KProperty<*>) = cachedValue
    inline operator fun setValue(thisRef: Any?, prop: KProperty<*>, value: Boolean) {
        cachedValue = value
        prefs.edit().putBoolean(key, value).apply()
    }
}

internal abstract class IntPrefField(
    private val prefs: SharedPreferences,
    private val key: String,
    defaultValue: Int
) {

    private var cachedValue = prefs.getInt(key, defaultValue)

    inline operator fun getValue(thisRef: Any?, prop: KProperty<*>) = cachedValue
    inline operator fun setValue(thisRef: Any?, prop: KProperty<*>, value: Int) {
        cachedValue = value
        prefs.edit().putInt(key, value).apply()
    }
}

internal abstract class LongPrefField(
    private val prefs: SharedPreferences,
    private val key: String,
    defaultValue: Long
) {

    private var cachedValue = prefs.getLong(key, defaultValue)

    inline operator fun getValue(thisRef: Any?, prop: KProperty<*>) = cachedValue
    inline operator fun setValue(thisRef: Any?, prop: KProperty<*>, value: Long) {
        cachedValue = value
        prefs.edit().putLong(key, value).apply()
    }
}

internal abstract class FloatPrefField(
    private val prefs: SharedPreferences,
    private val key: String,
    defaultValue: Float
) {

    private var cachedValue = prefs.getFloat(key, defaultValue)

    inline operator fun getValue(thisRef: Any?, prop: KProperty<*>) = cachedValue
    inline operator fun setValue(thisRef: Any?, prop: KProperty<*>, value: Float) {
        cachedValue = value
        prefs.edit().putFloat(key, value).apply()
    }
}

internal abstract class StringPrefField(
    private val prefs: SharedPreferences,
    private val key: String,
    defaultValue: String
) {

    private var cachedValue = prefs.getString(key, defaultValue)!!

    inline operator fun getValue(thisRef: Any?, prop: KProperty<*>) = cachedValue
    inline operator fun setValue(thisRef: Any?, prop: KProperty<*>, value: String) {
        cachedValue = value
        prefs.edit().putString(key, value).apply()
    }
}