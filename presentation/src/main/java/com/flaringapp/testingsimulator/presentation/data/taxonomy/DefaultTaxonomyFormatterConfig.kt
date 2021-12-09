package com.flaringapp.testingsimulator.presentation.data.taxonomy

import com.flaringapp.testingsimulator.core.data.color.ColorProvider
import com.flaringapp.testingsimulator.presentation.R

class DefaultTaxonomyFormatterConfig(
    override val labelTextColor: Int,
    override val labelTextSizeDp: Int = 14,
    override val isLabelBold: Boolean = false,
    override val valueTextColor: Int,
    override val valueTextSizeDp: Int = 16,
    override val isValueBold: Boolean = true,
    override val spacesBetweenAmount: Int = 2,
) : TaxonomyFormatterConfig {

    companion object {
        fun customize(
            colorProvider: ColorProvider,
            labelTextColor: Int = colorProvider.getColor(R.color.gray_light),
            labelTextSize: Int = 14,
            isLabelBold: Boolean = false,
            valueTextColor: Int = colorProvider.getColor(R.color.gray_dark),
            valueTextSize: Int = 16,
            isValueBold: Boolean = true,
            spacesBetweenAmount: Int = 2,
        ) = DefaultTaxonomyFormatterConfig(
            labelTextColor = labelTextColor,
            labelTextSizeDp = labelTextSize,
            isLabelBold = isLabelBold,
            valueTextColor = valueTextColor,
            valueTextSizeDp = valueTextSize,
            isValueBold = isValueBold,
            spacesBetweenAmount = spacesBetweenAmount,
        )

        fun withDefaultColors(colorProvider: ColorProvider) = DefaultTaxonomyFormatterConfig(
            labelTextColor = colorProvider.getColor(R.color.gray_light),
            valueTextColor = colorProvider.getColor(R.color.gray_dark),
        )
    }

}