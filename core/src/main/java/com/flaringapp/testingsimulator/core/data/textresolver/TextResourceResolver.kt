package com.flaringapp.testingsimulator.core.data.textresolver

import androidx.annotation.StringRes
import com.flaringapp.testingsimulator.core.data.textprovider.TextProvider
import kotlinx.parcelize.Parcelize

@Parcelize
class TextResourceResolver(
    @StringRes
    private val textRes: Int
) : TextResolver {

    override fun resolveText(textProvider: TextProvider): CharSequence {
        return textProvider.getString(textRes)
    }
}