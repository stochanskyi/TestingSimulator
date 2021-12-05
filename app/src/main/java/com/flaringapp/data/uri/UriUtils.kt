package com.flaringapp.data.uri

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.flaringapp.app.Constants
import java.io.File

object UriUtils {

    fun applicationUriFromFile(context: Context, file: File): Uri {
        return FileProvider.getUriForFile(
            context,
            Constants.FILE_PROVIDER,
            file
        )
    }

}