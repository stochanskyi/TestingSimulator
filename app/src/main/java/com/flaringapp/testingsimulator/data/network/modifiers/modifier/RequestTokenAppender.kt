package com.flaringapp.testingsimulator.data.network.modifiers.modifier

import com.flaringapp.testingsimulator.app.common.takeIfNotEmpty
import com.flaringapp.testingsimulator.data.network.NetworkConstants
import com.flaringapp.testingsimulator.data.storage.DataStorage
import okhttp3.Request
import org.koin.core.context.GlobalContext

class RequestTokenAppender : RequestModifier {

    private val dataStorage: DataStorage by GlobalContext.get().inject()

    override fun applyChanges(builder: Request.Builder) {
        val token = dataStorage.token.takeIfNotEmpty() ?: return
        builder.header(NetworkConstants.API_KEY, NetworkConstants.formatApiKey(token))
    }
}