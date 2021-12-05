package com.flaringapp.data.textprovider

import androidx.annotation.StringRes
import com.flaringapp.data.text.TextProvider
import com.flaringapp.data.textprovider.TextResolver
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