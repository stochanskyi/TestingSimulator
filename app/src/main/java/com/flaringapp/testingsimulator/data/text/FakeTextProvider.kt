package com.flaringapp.testingsimulator.data.text

class FakeTextProvider: TextProvider {

    override fun getText(res: Int): CharSequence {
        return ""
    }

    override fun getText(res: Int, vararg params: Any): CharSequence {
        return params.filterIsInstance<CharSequence>().joinToString(", ")
    }
}