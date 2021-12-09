package com.flaringapp.testingsimulator.core.data.textprovider

class FakeTextProvider: TextProvider {

    override fun getText(res: Int): CharSequence {
        return ""
    }

    override fun getText(res: Int, vararg params: Any): CharSequence {
        return params.filterIsInstance<CharSequence>().joinToString(", ")
    }

    override fun formatName(firstName: String, lastName: String): String {
        return "$firstName $lastName"
    }

    override fun formatPercent(value: String): String {
        return "$value%"
    }
}