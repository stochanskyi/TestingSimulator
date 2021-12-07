package com.flaringapp.testingsimulator.core.data.network.common.source

import android.content.Context
import android.net.Uri
import org.koin.core.context.GlobalContext
import java.io.File

class UriFileSource(
    private val uri: Uri
) : FileSource {

    override fun provideFile(): File? = null

    override fun provideByteArray(): ByteArray? {
        val context: Context = GlobalContext.get().get()
        return context.contentResolver.openInputStream(uri)?.use {
            it.readBytes()
        }
    }
}