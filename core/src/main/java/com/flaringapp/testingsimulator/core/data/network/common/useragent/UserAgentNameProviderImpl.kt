package com.flaringapp.testingsimulator.core.data.network.common.useragent

import android.content.Context
import android.os.Build
import okhttp3.OkHttp

class UserAgentNameProviderImpl(
    private val context: Context,
    private val versionCode: String,
    private val versionName: String,
) : UserAgentProvider {

    override fun provideUserAgent(): String {
        return "${getApplicationName(context)}/" +
            "$versionName " +
            "(${context.packageName}; " +
            "build:${versionCode} " +
            "Android SDK ${Build.VERSION.SDK_INT}) " +
            "okhttp/${OkHttp.VERSION} " +
            getDeviceName()
    }

    private fun getApplicationName(context: Context): String {
        val applicationInfo = context.applicationInfo
        val stringId = applicationInfo.labelRes
        return if (stringId == 0) applicationInfo.nonLocalizedLabel.toString()
        else context.getString(stringId)
    }

    private fun getDeviceName(): String {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL

        return if (model.startsWith(manufacturer)) model
        else "$manufacturer $model"
    }

}