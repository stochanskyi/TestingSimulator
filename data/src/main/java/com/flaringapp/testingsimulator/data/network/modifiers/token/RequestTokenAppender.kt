package com.flaringapp.testingsimulator.data.network.modifiers.token

import com.flaringapp.testingsimulator.core.data.network.common.modifier.RequestModifier
import com.flaringapp.testingsimulator.data.network.NetworkConstants
import com.flaringapp.testingsimulator.domain.storage.DataStorage
import okhttp3.Request
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RequestTokenAppender : RequestModifier, KoinComponent {

    private val dataStorage: DataStorage by inject()

    override fun applyChanges(builder: Request.Builder) {
        val token = dataStorage.token ?: return
        builder.header(NetworkConstants.API_KEY, NetworkConstants.formatApiKey(token))
    }
}