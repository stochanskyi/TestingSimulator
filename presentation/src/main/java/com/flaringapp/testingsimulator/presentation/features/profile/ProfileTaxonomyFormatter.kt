package com.flaringapp.testingsimulator.presentation.features.profile

import android.content.Context
import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.annotation.StringRes
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import com.flaringapp.testingsimulator.core.presentation.utils.span.CustomTypefaceSpan
import com.flaringapp.testingsimulator.presentation.R

class ProfileTaxonomyFormatter(
    private val context: Context
) {

    private val prefixColor = context.getColor(R.color.gray_light)
    private val contentColor = context.getColor(R.color.gray_dark)

    private val contentTypeface = Typeface.create("sans-serif-light", Typeface.NORMAL)

    fun formatTaxonomyText(
        @StringRes prefixStringRes: Int,
        content: String,
    ): CharSequence {
        if (content.isEmpty()) return ""

        return buildSpannedString {
            prefix {
                append(context.getString(prefixStringRes))
                append(": ")
            }
            content {
                append(content)
            }
        }
    }

    private fun SpannableStringBuilder.prefix(
        builderAction: SpannableStringBuilder.() -> Unit
    ): SpannableStringBuilder {
        return inSpans(
            ForegroundColorSpan(prefixColor),
            CustomTypefaceSpan(Typeface.SANS_SERIF),
            builderAction = builderAction
        )
    }

    private fun SpannableStringBuilder.content(
        builderAction: SpannableStringBuilder.() -> Unit
    ): SpannableStringBuilder {
        return inSpans(
            ForegroundColorSpan(contentColor),
            CustomTypefaceSpan(contentTypeface),
            builderAction = builderAction
        )
    }

}