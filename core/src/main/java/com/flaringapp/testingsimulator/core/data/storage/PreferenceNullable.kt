package com.flaringapp.testingsimulator.core.data.storage

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PreferenceNullable<T>(
    private val prefs: SharedPreferences,
    private val name: String,
    private val default: T? = null
) : ReadWriteProperty<Any?, T?> {

    override fun getValue(thisRef: Any?, property: KProperty<*>): T? {
        return findPreferenceNullable(name, default)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        putPreference(name, value)
    }

    @Suppress("unchecked_cast")
    private fun <T> findPreferenceNullable(
        name: String,
        default: T?
    ): T? = with(prefs) {
        val res: Any? = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("This type cannot be saved into Preferences")
        }
        res as? T
    }

    private fun <T> putPreference(name: String, value: T) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("This type cannot be saved into Preferences")
        }.apply()
    }
}