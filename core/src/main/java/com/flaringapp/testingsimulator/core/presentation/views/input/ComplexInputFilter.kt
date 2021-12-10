package com.flaringapp.testingsimulator.core.presentation.views.input

import android.text.InputFilter
import android.text.Spanned

open class ComplexInputFilter (
    vararg filters: InputFilter
): InputFilter {

    private val filters = filters.toList()

    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        filters.forEach { inputFilter ->
            inputFilter.filter(source, start, end, dest, dstart, dend)
                .takeIf { it != null }
                ?.let { return it }
        }
        return null
    }
}