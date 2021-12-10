package com.flaringapp.testingsimulator.core.presentation.views.input

import android.text.InputFilter
import android.text.Spanned

abstract class CharacterInputFilter : InputFilter {

    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        val filteredBuilder = StringBuilder(end - start)

        for (i in start until end) {
            val char: Char = source[i]
            if (matchesChar(char)) {
                filteredBuilder.append(char)
            }
        }

        if (filteredBuilder.length == source.length) return null
        return filteredBuilder
    }

    protected abstract fun matchesChar(char: Char): Boolean

}