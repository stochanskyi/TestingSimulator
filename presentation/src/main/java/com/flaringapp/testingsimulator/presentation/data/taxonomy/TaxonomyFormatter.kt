package com.flaringapp.testingsimulator.presentation.data.taxonomy

interface TaxonomyFormatter {

    var config: TaxonomyFormatterConfig

    fun format(taxonomy: List<TaxonomyFormattable>): CharSequence

    fun format(taxonomy: TaxonomyFormattable): CharSequence

    fun format(labelRes: Int, value: CharSequence): CharSequence

    fun format(label: CharSequence, value: CharSequence): CharSequence

}