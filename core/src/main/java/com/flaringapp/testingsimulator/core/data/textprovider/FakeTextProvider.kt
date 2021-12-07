package com.flaringapp.testingsimulator.core.data.textprovider

class FakeTextProvider: TextProvider {

    override fun getText(res: Int): CharSequence {
        return ""
    }

    override fun getText(res: Int, vararg params: Any): CharSequence {
        return params.filterIsInstance<CharSequence>().joinToString(", ")
    }
}