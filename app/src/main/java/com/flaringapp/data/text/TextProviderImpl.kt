package com.flaringapp.data.text

import android.content.Context

class TextProviderImpl(
    private val context: Context
): TextProvider {

    override fun getText(res: Int): CharSequence {
        return context.getString(res)
    }

    override fun getText(res: Int, vararg params: Any): CharSequence {
        return context.getString(res, *params)
    }
}