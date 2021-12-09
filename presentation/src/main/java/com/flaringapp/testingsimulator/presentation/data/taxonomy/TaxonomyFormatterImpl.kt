package com.flaringapp.testingsimulator.presentation.data.taxonomy

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import com.flaringapp.testingsimulator.core.data.color.ColorProvider
import com.flaringapp.testingsimulator.core.data.textprovider.TextProvider
import com.flaringapp.testingsimulator.core.presentation.utils.span.CustomTypefaceSpan

class TaxonomyFormatterImpl(
    private val textProvider: TextProvider,
    colorProvider: ColorProvider,
) : TaxonomyFormatter {

    private val regularTypeface: Typeface by lazy(LazyThreadSafetyMode.NONE) {
        Typeface.DEFAULT
    }
    private val boldTypeface: Typeface by lazy(LazyThreadSafetyMode.NONE) {
        Typeface.create("sans-serif-medium", Typeface.NORMAL)
    }

    override var config: TaxonomyFormatterConfig = DefaultTaxonomyFormatterConfig
        .withDefaultColors(colorProvider)

    override fun format(taxonomy: List<TaxonomyFormattable>): CharSequence {
        if (taxonomy.isEmpty()) return ""

        val formattedTaxonomy = Array<CharSequence>(taxonomy.size) { "" }

        formattedTaxonomy[0] = format(taxonomy.first())
        for (i in 1 until taxonomy.size) {
            formattedTaxonomy[i] = buildSpannedString {
                append(format(taxonomy[i]))
                appendLine()
            }
        }

        return TextUtils.concat(*formattedTaxonomy)
    }

    override fun format(taxonomy: TaxonomyFormattable): CharSequence {
        return format(taxonomy.label, taxonomy.value)
    }

    override fun format(labelRes: Int, value: CharSequence): CharSequence {
        val label = textProvider.getText(labelRes)
        return format(label, value)
    }

    override fun format(label: CharSequence, value: CharSequence): CharSequence {
        return buildSpannedString {
            label {
                append(label)
                append(":")
                repeat(config.spacesBetweenAmount) {
                    append(" ")
                }
            }
            value {
                append(value)
            }
        }
    }

    private fun SpannableStringBuilder.label(
        builderAction: SpannableStringBuilder.() -> Unit
    ): SpannableStringBuilder {
        return inSpans(
            ForegroundColorSpan(config.labelTextColor),
            CustomTypefaceSpan(resolveTypeface(config.isLabelBold)),
            AbsoluteSizeSpan(config.labelTextSizeDp, true),
            builderAction = builderAction
        )
    }

    private fun SpannableStringBuilder.value(
        builderAction: SpannableStringBuilder.() -> Unit
    ): SpannableStringBuilder {
        return inSpans(
            ForegroundColorSpan(config.valueTextColor),
            CustomTypefaceSpan(resolveTypeface(config.isValueBold)),
            AbsoluteSizeSpan(config.valueTextSizeDp, true),
            builderAction = builderAction
        )
    }

    private fun resolveTypeface(isBold: Boolean): Typeface {
        return if (isBold) boldTypeface
        else regularTypeface
    }

}