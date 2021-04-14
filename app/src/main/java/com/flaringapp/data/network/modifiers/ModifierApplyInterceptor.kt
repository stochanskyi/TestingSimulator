package com.flaringapp.data.network.modifiers

import okhttp3.Interceptor
import okhttp3.Response

class ModifierApplyInterceptor(
    private val dataCache: RequestDataCache
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val modifier = dataCache.resolveRequestModifier(request)
        if (modifier != null) {
            val builder = request.newBuilder()
            modifier.applyChanges(builder)
            request = builder.build()
        }

        return chain.proceed(request)
    }

}