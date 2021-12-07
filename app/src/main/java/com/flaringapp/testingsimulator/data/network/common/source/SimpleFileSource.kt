package com.flaringapp.testingsimulator.data.network.common.source

import java.io.File

class SimpleFileSource(
    private val file: File
) : FileSource {

    override fun provideFile(): File = file

    override fun provideByteArray(): ByteArray? = null

}