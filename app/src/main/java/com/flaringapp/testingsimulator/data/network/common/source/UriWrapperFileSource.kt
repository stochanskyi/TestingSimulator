package com.flaringapp.testingsimulator.data.network.common.source

import android.content.Context
import com.flaringapp.testingsimulator.data.uri.UriWrapper
import org.koin.core.context.GlobalContext
import java.io.File

class UriWrapperFileSource(
    private val wrapper: UriWrapper
) : FileSource {

    override fun provideFile(): File? = null

    override fun provideByteArray(): ByteArray? {
        val context: Context = GlobalContext.get().get()
        return context.contentResolver.openInputStream(wrapper.uri)?.use {
            it.readBytes()
        }
    }
}