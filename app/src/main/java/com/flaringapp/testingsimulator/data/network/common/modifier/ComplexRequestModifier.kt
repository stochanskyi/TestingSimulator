package com.flaringapp.testingsimulator.data.network.common.modifier

import okhttp3.Request

class ComplexRequestModifier(
    private val modifiers: Collection<RequestModifier>
): RequestModifier {

    override fun applyChanges(builder: Request.Builder) {
        modifiers.forEach { it.applyChanges(builder) }
    }
}