package com.flaringapp.data.textprovider

import com.flaringapp.data.text.TextProvider
import kotlinx.parcelize.Parcelize

@Parcelize
class TextNamedResolver(
    private val name: CharSequence
) : TextResolver {
    override fun resolveText(textProvider: TextProvider): CharSequence = name
}