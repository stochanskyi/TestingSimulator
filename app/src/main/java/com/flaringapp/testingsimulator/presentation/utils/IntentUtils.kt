package com.flaringapp.testingsimulator.presentation.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import com.flaringapp.testingsimulator.data.uri.UriUtils
import java.io.File

@SuppressLint("QueryPermissionsNeeded")
fun Context.startCameraIntent(file: File): Intent? {
    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    if (intent.resolveActivity(packageManager) == null) {
        return null
    }

    val photoUri = UriUtils.applicationUriFromFile(this, file)
    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
    return intent
}

