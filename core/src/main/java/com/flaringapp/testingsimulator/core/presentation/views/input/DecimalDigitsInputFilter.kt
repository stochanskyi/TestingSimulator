package com.flaringapp.testingsimulator.core.presentation.views.input

import android.text.InputFilter
import android.text.Spanned
import java.util.regex.Matcher
import java.util.regex.Pattern

class DecimalDigitsInputFilter(
    digitsBeforeDot: Int,
    digitsAfterDot: Int
) : InputFilter {

    private val mPattern =
        Pattern.compile("[0-9]{0,$digitsBeforeDot}+((\\.[0-9]{0,$digitsAfterDot})?)|(\\.)?")

    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        val matcher: Matcher = mPattern.matcher(dest.toString() + source.toString())
        return if (!matcher.matches()) "" else null
    }
}