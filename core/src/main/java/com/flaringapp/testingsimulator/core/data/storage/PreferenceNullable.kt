package com.flaringapp.testingsimulator.core.data.storage

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

class PreferenceNullable<T>(
    private val prefs: SharedPreferences,
    private val name: String,
    private val clazz: KClass<*>,
) : ReadWriteProperty<Any?, T?> {

    override fun getValue(thisRef: Any?, property: KProperty<*>): T? {
        return findPreferenceNullable(name)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        putPreference(name, value)
    }

    @Suppress("unchecked_cast")
    private fun <T> findPreferenceNullable(
        name: String,
    ): T? = with(prefs) {
        val res: Any? = when (clazz) {
            String::class -> getString(name, null)
            else -> throw IllegalArgumentException("This type cannot be saved into Preferences")
        }
        res as? T
    }

    private fun <T> putPreference(name: String, value: T) = with(prefs.edit()) {
        when (value) {
            is String -> putString(name, value)
            else -> throw IllegalArgumentException("This type cannot be saved into Preferences")
        }.apply()
    }
}