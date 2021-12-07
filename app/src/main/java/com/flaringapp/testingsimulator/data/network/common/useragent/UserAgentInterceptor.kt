package com.flaringapp.testingsimulator.data.network.common.useragent

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class UserAgentInterceptor(private val userAgent: String) : Interceptor {

    companion object {
        private const val USER_AGENT = "User-Agent"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val requestWithUserAgent: Request = originalRequest.newBuilder()
            .header(USER_AGENT, userAgent)
            .build()
        return chain.proceed(requestWithUserAgent)
    }
}