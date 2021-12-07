package com.flaringapp.testingsimulator.data.textprovider

import com.flaringapp.testingsimulator.data.text.TextProvider
import kotlinx.parcelize.Parcelize

@Parcelize
class TextNamedResolver(
    private val name: CharSequence
) : TextResolver {
    override fun resolveText(textProvider: TextProvider): CharSequence = name
}