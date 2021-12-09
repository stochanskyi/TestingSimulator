package com.flaringapp.testingsimulator.presentation.data.taxonomy

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import com.flaringapp.testingsimulator.core.app.common.isEmpty
import com.flaringapp.testingsimulator.core.data.color.ColorProvider
import com.flaringapp.testingsimulator.core.data.textprovider.TextProvider
import com.flaringapp.testingsimulator.core.presentation.utils.span.CustomTypefaceSpan
import com.flaringapp.testingsimulator.domain.features.taxonomy.SimpleTaxonomyFormattable
import com.flaringapp.testingsimulator.domain.features.taxonomy.TaxonomyFormattable
import com.flaringapp.testingsimulator.domain.features.taxonomy.TaxonomyFormatter
import com.flaringapp.testingsimulator.domain.features.taxonomy.TaxonomyFormatterConfig

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

    override fun format(taxonomy: Map<out CharSequence, CharSequence>): CharSequence {
        val taxonomySequence = taxonomy.asSequence().map { (label, value) ->
            SimpleTaxonomyFormattable(label, value)
        }
        return format(taxonomySequence)
    }

    override fun format(taxonomy: List<TaxonomyFormattable>): CharSequence {
        return format(taxonomy.asSequence())
    }

    override fun format(taxonomy: Sequence<TaxonomyFormattable>): CharSequence {
        if (taxonomy.isEmpty()) return ""

        val taxonomySize = taxonomy.count()
        val formattedTaxonomy = Array<CharSequence>(taxonomySize) { "" }

        formattedTaxonomy[0] = format(taxonomy.first())

        taxonomy
            .drop(1)
            .forEachIndexed { i, item ->
                formattedTaxonomy[i + 1] = buildSpannedString {
                    appendLine()
                    append(format(item))
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