package com.flaringapp.testingsimulator.data.network.common.useragent

import android.content.Context
import android.os.Build
import com.flaringapp.testingsimulator.BuildConfig
import okhttp3.OkHttp

class UserAgentNameProviderImpl(
    private val context: Context
) : UserAgentProvider {

    override fun provideUserAgent(): String {
        return "${getApplicationName(context)}/" +
            "${BuildConfig.VERSION_NAME} " +
            "(${context.packageName}; " +
            "build:${BuildConfig.VERSION_CODE} " +
            "Android SDK ${Build.VERSION.SDK_INT}) " +
            "okhttp/${OkHttp.VERSION} " +
            getDeviceName()
    }

    private fun getApplicationName(context: Context): String? {
        val applicationInfo = context.applicationInfo
        val stringId = applicationInfo.labelRes
        return if (stringId == 0) applicationInfo.nonLocalizedLabel.toString() else context.getString(
            stringId
        )
    }

    private fun getDeviceName(): String {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL

        return if (model.startsWith(manufacturer)) model
        else "$manufacturer $model"
    }

}