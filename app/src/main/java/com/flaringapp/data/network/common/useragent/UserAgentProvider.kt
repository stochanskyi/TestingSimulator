package com.flaringapp.data.network.common.useragent

interface UserAgentProvider {

    fun provideUserAgent(): String

}