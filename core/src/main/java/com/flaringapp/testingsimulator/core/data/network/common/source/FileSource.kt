package com.flaringapp.testingsimulator.core.data.network.common.source

import java.io.File

interface FileSource {

    fun provideFile(): File?

    fun provideByteArray(): ByteArray?

}