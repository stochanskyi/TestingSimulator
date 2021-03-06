package com.flaringapp.testingsimulator.core.data.textprovider

import androidx.annotation.StringRes

interface TextProvider {

    fun getText(@StringRes res: Int): CharSequence
    fun getString(@StringRes res: Int): String {
        return getText(res).toString()
    }

    fun getText(@StringRes res: Int, vararg params: Any): CharSequence

    fun formatName(firstName: String, lastName: String): String

    fun formatPercent(value: String): String

}