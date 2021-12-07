package com.flaringapp.testingsimulator.core.data.textresolver

import com.flaringapp.testingsimulator.core.data.textprovider.TextProvider
import kotlinx.parcelize.Parcelize

@Parcelize
class TextNamedResolver(
    private val name: CharSequence
) : TextResolver {
    override fun resolveText(textProvider: TextProvider): CharSequence = name
}