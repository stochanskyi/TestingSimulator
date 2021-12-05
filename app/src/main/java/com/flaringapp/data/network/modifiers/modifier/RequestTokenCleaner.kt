package com.flaringapp.data.network.modifiers.modifier

import com.flaringapp.data.network.NetworkConstants
import okhttp3.Request

class RequestTokenCleaner : RequestModifier {

    override fun applyChanges(builder: Request.Builder) {
        builder.removeHeader(NetworkConstants.API_KEY)
    }
}