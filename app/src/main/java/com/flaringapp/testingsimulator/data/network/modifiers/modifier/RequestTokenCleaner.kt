package com.flaringapp.testingsimulator.data.network.modifiers.modifier

import com.flaringapp.testingsimulator.data.network.NetworkConstants
import okhttp3.Request

class RequestTokenCleaner : RequestModifier {

    override fun applyChanges(builder: Request.Builder) {
        builder.removeHeader(NetworkConstants.API_KEY)
    }
}