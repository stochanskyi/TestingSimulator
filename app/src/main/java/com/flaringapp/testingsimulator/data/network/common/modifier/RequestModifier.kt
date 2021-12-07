package com.flaringapp.testingsimulator.data.network.common.modifier

import okhttp3.Request

interface RequestModifier {

    fun applyChanges(builder: Request.Builder)

}