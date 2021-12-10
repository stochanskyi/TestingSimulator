package com.flaringapp.testingsimulator.core.presentation.views.input

import android.text.InputFilter
import android.text.Spanned

class StartOneZeroInputFilter: InputFilter {

    companion object {
        private const val ZERO = '0'
    }

    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        when (dest.length) {
            0 -> {
                val lastStartZeroIndex = indexOfLastStartZero(source)
                if (lastStartZeroIndex < 1) return null

                return if (lastStartZeroIndex == source.length - 1) ZERO.toString()
                else source.substring(lastStartZeroIndex + 1, source.length)
            }
            1 -> {
                val lastStartZeroIndex = indexOfLastStartZero(source)
                if (lastStartZeroIndex < 1) return null

                return if (lastStartZeroIndex == source.length - 1) ""
                else source.substring(lastStartZeroIndex + 1, source.length)
            }
        }

        return null
    }

    private fun indexOfLastStartZero(cs: CharSequence): Int {
        for (i in cs.indices) {
            if (cs[i] != ZERO) return i - 1
        }
        return -1
    }
}