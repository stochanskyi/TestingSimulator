package com.flaringapp.testingsimulator.core.presentation.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import com.flaringapp.testingsimulator.core.data.uri.UriUtils
import java.io.File

@SuppressLint("QueryPermissionsNeeded")
fun Context.startCameraIntent(file: File, authority: String): Intent? {
    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    if (intent.resolveActivity(packageManager) == null) {
        return null
    }

    val photoUri = UriUtils.applicationUriFromFile(this, authority, file)
    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
    return intent
}

