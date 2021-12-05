package com.flaringapp.data.network.common.source

import java.io.File

interface FileSource {

    fun provideFile(): File?

    fun provideByteArray(): ByteArray?

}