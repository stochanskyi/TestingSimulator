package com.flaringapp.testingsimulator.data.textprovider

import androidx.annotation.StringRes
import com.flaringapp.testingsimulator.data.text.TextProvider
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