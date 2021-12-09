package com.flaringapp.testingsimulator.domain.features.taxonomy

interface TaxonomyFormatter {

    var config: TaxonomyFormatterConfig

    fun format(taxonomy: Map<out CharSequence, CharSequence>): CharSequence

    fun format(taxonomy: List<TaxonomyFormattable>): CharSequence

    fun format(taxonomy: Sequence<TaxonomyFormattable>): CharSequence

    fun format(taxonomy: TaxonomyFormattable): CharSequence

    fun format(labelRes: Int, value: CharSequence): CharSequence

    fun format(label: CharSequence, value: CharSequence): CharSequence

}