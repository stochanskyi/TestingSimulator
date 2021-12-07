package com.flaringapp.testingsimulator.data.network.modifiers.modifier

import okhttp3.Request

interface RequestModifier {

    fun applyChanges(builder: Request.Builder)

}