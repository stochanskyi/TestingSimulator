package com.flaringapp.data.network.common.source

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody

fun FileSource.toMultipartBodyPart(
    key: String,
    type: String,
    name: String? = null
) = MultipartBody.Part.createFormData(
    key,
    name,
    createRequestBody(type)
)

fun FileSource.createRequestBody(type: String): RequestBody {
    return provideFile()?.asRequestBody(type.toMediaType())
        ?: provideByteArray()?.toRequestBody(type.toMediaType())
        ?: throw IllegalStateException("File source has no data")
}