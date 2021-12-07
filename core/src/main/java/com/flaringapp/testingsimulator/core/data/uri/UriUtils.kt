package com.flaringapp.testingsimulator.core.data.uri

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

object UriUtils {

    fun applicationUriFromFile(context: Context, authority: String, file: File): Uri {
        return FileProvider.getUriForFile(context, authority, file)
    }

}